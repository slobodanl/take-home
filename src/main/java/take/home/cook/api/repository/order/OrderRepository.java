package take.home.cook.api.repository.order;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;
import take.home.cook.api.model.order.Order;

import java.util.UUID;

@NoRepositoryBean
public interface OrderRepository extends CassandraRepository<Order, UUID> {
}
