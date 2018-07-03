package take.home.cook.api.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.user.domain.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
    List<User> findByUserKeyType(String type);
}