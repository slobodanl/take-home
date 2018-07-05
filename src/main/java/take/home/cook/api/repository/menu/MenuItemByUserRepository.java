package take.home.cook.api.repository.menu;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.menu.MenuItem;
import take.home.cook.api.model.menu.MenuItemByCategory;
import take.home.cook.api.model.menu.MenuItemByUser;

import java.util.UUID;

@Repository
public interface MenuItemByUserRepository extends CassandraRepository<MenuItemByUser, MenuItemByUser.Key> {
    MenuItemByUser findByKeyIdAndKeyItemId(UUID cookId , UUID itemId);
}
