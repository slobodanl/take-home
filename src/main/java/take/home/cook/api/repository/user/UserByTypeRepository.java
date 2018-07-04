package take.home.cook.api.repository.user;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.user.UserByType;
import take.home.cook.api.model.user.UserByTypeKey;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserByTypeRepository extends CassandraRepository<UserByType, UserByTypeKey> {
    List<UserByType> findAllByUserByTypeKeyType(String type);
    UserByType findByUserByTypeKeyTypeAndUserByTypeKeyId(String type , UUID id);
}