package take.home.cook.api.repository.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import take.home.cook.api.model.order.Order;
import take.home.cook.api.repository.menu.MenuItemByUserRepository;
import take.home.cook.api.repository.user.UserRepository;

import java.util.UUID;

@Configuration
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(
            final CassandraTemplate cassandraTemplate,
            final OrderByCookRepository orderByCookRepository,
            final OrderByUserRepository orderByUserRepository,
            final OrderByItemRepository orderByItemRepository,
            final MenuItemByUserRepository menuItemByUserRepository,
            final UserRepository userRepository) {
        final CassandraPersistentEntity<?> entity =
                cassandraTemplate
                        .getConverter()
                        .getMappingContext()
                        .getRequiredPersistentEntity(Order.class);
        final CassandraEntityInformation<Order, UUID> metadata =
                new MappingCassandraEntityInformation<>(
                        (CassandraPersistentEntity<Order>) entity, cassandraTemplate.getConverter());
        return new OrderRepositoryImpl(
                metadata,
                cassandraTemplate,
                orderByCookRepository,
                orderByUserRepository,
                orderByItemRepository,
                menuItemByUserRepository,
                userRepository);
    }
}
