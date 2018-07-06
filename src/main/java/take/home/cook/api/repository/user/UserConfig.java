package take.home.cook.api.repository.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import take.home.cook.api.model.user.User;

@Configuration
public class UserConfig {

    @Bean
    public UserRepository userRepository(
            final CassandraTemplate cassandraTemplate,
            final UserByTypeRepository userByTypeRepository) {
        final CassandraPersistentEntity<?> entity =
                cassandraTemplate
                        .getConverter()
                        .getMappingContext()
                        .getRequiredPersistentEntity(User.class);
        final CassandraEntityInformation<User, MapId> metadata =
                new MappingCassandraEntityInformation<>(
                        (CassandraPersistentEntity<User>) entity, cassandraTemplate.getConverter());
        return new UserRepositoryImpl(
                metadata,
                cassandraTemplate,
                userByTypeRepository);
    }
}


