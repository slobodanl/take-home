package take.home.cook.api.repository.user;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;
import take.home.cook.api.model.user.User;

import java.util.UUID;

@NoRepositoryBean
public interface UserRepository  extends CassandraRepository<User, UUID> {

}
