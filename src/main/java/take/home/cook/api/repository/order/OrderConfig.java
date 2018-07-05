package take.home.cook.api.repository.order;

import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import take.home.cook.api.model.order.Order;

import java.util.UUID;

public class OrderConfig {

    @Bean
    public OrderRepository userRepository(
            final CassandraTemplate cassandraTemplate,
            final OrderByCookRepository orderByCookRepository,
            final OrderByUserRepository orderByUserRepository,
            final OrderByItemRepository orderByItemRepository) {
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
                cassandraTemplate);
    }
}
