package take.home.cook.api.repository.menu;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import take.home.cook.api.model.menu.MenuItem;
import take.home.cook.api.model.menu.MenuItemByCategory;
import take.home.cook.api.model.menu.MenuItemByUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuItemRepositoryImpl extends SimpleCassandraRepository<MenuItem, MapId>
        implements MenuItemRepository {
    private final MenuItemByCategoryRepository menuItemByCategoryRepository;
    private final MenuItemByUserRepository menuItemByUserRepository;
    private final CassandraOperations operations;


    public MenuItemRepositoryImpl(CassandraEntityInformation<MenuItem, MapId> metadata, CassandraOperations operations , MenuItemByUserRepository menuItemByUserRepository , MenuItemByCategoryRepository menuItemByCategoryRepository) {
        super(metadata, operations);
        this.operations = operations;
        this.menuItemByCategoryRepository = menuItemByCategoryRepository;
        this.menuItemByUserRepository = menuItemByUserRepository;
    }


    @Override
    public <S extends MenuItem> S insert(S menuItem) {
        CassandraBatchOperations batchOpts = operations.batchOps();
        menuItem.setKey(new MenuItem.Key(UUIDs.timeBased() , menuItem.getKey().getUserId() , menuItem.getKey().getItemAdded()));
        insertMenuItemByUser(menuItem , batchOpts);
        insertMenuItemByCategory(menuItem, batchOpts);
        batchOpts.insert(menuItem);
        batchOpts.execute();
        return menuItem;
    }

    private void insertMenuItemByUser(MenuItem menuItem , CassandraBatchOperations batchOpts) {
        batchOpts.insert(
                new MenuItemByUser( new MenuItemByUser.Key(menuItem.getKey().getUserId() , menuItem.getKey().getId() , menuItem.getKey().getItemAdded()) , menuItem)
        );
    }

    private void insertMenuItemByCategory(MenuItem menuItem , CassandraBatchOperations batchOpts) {
       menuItem.getCategories().forEach(category ->
                batchOpts.insert(new MenuItemByCategory(new MenuItemByCategory.Key(category , menuItem.getKey().getUserId() , menuItem.getKey().getId() , menuItem.getKey().getItemAdded()) , menuItem))
       );
    }


    @Override
    public <S extends MenuItem> S save(S menuItem) {
        return insert(menuItem);
    }

    @Override
    public <S extends MenuItem> List<S> insert(Iterable<S> entities) {
        List<S> menuItems = new ArrayList<>();
        entities.forEach(e-> menuItems.add(insert(e)));
        return menuItems;
    }

    @Override
    public <S extends MenuItem> List<S> saveAll(Iterable<S> entities) {
        return insert(entities);
    }
}
