package take.home.cook.api.repository.menu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import take.home.cook.api.model.menu.MenuItem;

import java.util.UUID;

@Configuration
public class MenuConfig {
    @Bean
    public MenuItemRepository createMenuItemRepository(final CassandraTemplate cassandraTemplate, MenuItemByCategoryRepository menuItemByCategoryRepository , MenuItemByUserRepository menuItemByUserRepository) {
        final CassandraPersistentEntity<?> entity =
                cassandraTemplate
                        .getConverter()
                        .getMappingContext()
                        .getRequiredPersistentEntity(MenuItem.class);
        final CassandraEntityInformation<MenuItem, MapId> metadata =
                new MappingCassandraEntityInformation<>(
                        (CassandraPersistentEntity<MenuItem>) entity, cassandraTemplate.getConverter());
        return new MenuItemRepositoryImpl(metadata , cassandraTemplate , menuItemByUserRepository , menuItemByCategoryRepository );
    }

}
