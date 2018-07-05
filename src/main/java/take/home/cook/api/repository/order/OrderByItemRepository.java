package take.home.cook.api.repository.order;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.order.OrderByItem;

@Repository
public interface OrderByItemRepository extends CassandraRepository<OrderByItem, OrderByItem.Key> {
}
