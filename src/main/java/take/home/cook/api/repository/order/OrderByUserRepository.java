package take.home.cook.api.repository.order;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import take.home.cook.api.model.order.OrderByUser;

@Repository
public interface OrderByUserRepository extends CassandraRepository<OrderByUser, OrderByUser.Key> {
}
