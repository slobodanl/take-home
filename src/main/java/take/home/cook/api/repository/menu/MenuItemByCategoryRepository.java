package take.home.cook.api.repository.menu;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.menu.MenuItemByCategory;

import java.util.List;

@Repository
public interface MenuItemByCategoryRepository extends CassandraRepository<MenuItemByCategory, MenuItemByCategory.Key> {
    List<MenuItemByCategory> findAllByKeyId(String category);
}
