package take.home.cook.api.repository.user;

import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;
import take.home.cook.api.model.user.User;

@NoRepositoryBean
public interface UserRepository  extends CassandraRepository<User, MapId> {

}
