package take.home.cook.api.repository.order;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.order.OrderByCook;

@Repository
public interface OrderByCookRepository extends CassandraRepository<OrderByCook , OrderByCook.Key> {
}
