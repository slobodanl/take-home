package take.home.cook.api.repository.user;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import take.home.cook.api.model.user.User;
import take.home.cook.api.model.user.UserByType;
import take.home.cook.api.model.user.UserKey;
import take.home.cook.api.model.user.UserByTypeKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl extends SimpleCassandraRepository<User, UUID>
        implements UserRepository {

    private final CassandraTemplate cassandraTemplate;
    private final UserByTypeRepository userByTypeRepository;

    public UserRepositoryImpl(CassandraEntityInformation<User, UUID> metadata, CassandraTemplate cassandraTemplate , UserByTypeRepository userByTypeRepository) {
        super(metadata, cassandraTemplate);
        this.cassandraTemplate = cassandraTemplate;
        this.userByTypeRepository = userByTypeRepository;
    }

    @Override
    public <S extends User> S insert(S user) {
        user.setId(new UserKey(UUIDs.timeBased() , user.getId().getType()));
        final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        insertByType(user , batchOps);
        batchOps.insert(user);
        batchOps.execute();
        return user;
    }

    private void insertByType(final User user , final CassandraBatchOperations batchOps) {
        batchOps.insert(new UserByType(new UserByTypeKey(user.getId().getType() , user.getId().getId()) , user.getFirstName() , user.getLastName() , user.getAddress() , user.getPhone()));
    }

    private void deleteByType(final User user , final CassandraBatchOperations batchOps) {
        batchOps.delete(userByTypeRepository.findByUserByTypeKeyTypeAndUserByTypeKeyId(user.getId().getType() , user.getId().getId()));
    }

    @Override
    public void delete(User user) {
        final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        deleteByType(user , batchOps);
        batchOps.delete(user);
        batchOps.execute();
    }




    @Override
    public <S extends User> S save(S user) {
        return insert(user);
    }

    @Override
    public <S extends User> List<S> insert(Iterable<S> entities) {
        List<S> users = new ArrayList<>();
        entities.forEach(e -> users.add(insert(e)));
        return users;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return insert(entities);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        entities.forEach(this::delete);
    }


}
