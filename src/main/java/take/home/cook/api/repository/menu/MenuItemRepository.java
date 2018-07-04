package take.home.cook.api.repository.menu;

import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;
import take.home.cook.api.model.menu.MenuItem;

import java.util.UUID;

@NoRepositoryBean
public interface MenuItemRepository extends CassandraRepository<MenuItem, MapId> {

}

