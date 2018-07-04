package take.home.cook.api.repository.menu;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.menu.MenuItemByCategory;
import take.home.cook.api.model.menu.MenuItemByUser;

@Repository
public interface MenuItemByUserRepository extends CassandraRepository<MenuItemByUser, MenuItemByUser.Key> {
}
