package take.home.cook.api.repository.order;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import take.home.cook.api.model.order.Order;
import take.home.cook.api.model.order.OrderByCook;
import take.home.cook.api.model.order.OrderByItem;
import take.home.cook.api.model.order.OrderByUser;
import take.home.cook.api.model.user.User;
import take.home.cook.api.repository.menu.MenuItemByUserRepository;
import take.home.cook.api.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderRepositoryImpl extends SimpleCassandraRepository<Order, UUID>
        implements OrderRepository {
    private final OrderByCookRepository orderByCookRepository;
    private final OrderByUserRepository orderByUserRepository;
    private final OrderByItemRepository orderByItemRepository;
    private final MenuItemByUserRepository menuItemByUserRepository;
    private final UserRepository userRepository;
    private final CassandraTemplate cassandraTemplate;
    public OrderRepositoryImpl(CassandraEntityInformation<Order, UUID> metadata,
                               CassandraTemplate operations ,
                               final OrderByCookRepository orderByCookRepository,
                               final OrderByUserRepository orderByUserRepository,
                               final OrderByItemRepository orderByItemRepository,
                               final MenuItemByUserRepository menuItemByUserRepository,
                               final UserRepository userRepository) {
        super(metadata, operations);
        this.cassandraTemplate = operations;
        this.orderByCookRepository = orderByCookRepository;
        this.orderByItemRepository = orderByItemRepository;
        this.orderByUserRepository = orderByUserRepository;
        this.menuItemByUserRepository = menuItemByUserRepository;
        this.userRepository = userRepository;
    }

    @Override
    public <S extends Order> S insert(S order) {
        CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        order.setId(new Order.Key(UUIDs.timeBased() , new Date()));
        insertOrderByUser(order , batchOps);
        insertOrderByCook(order , batchOps);
        insertOrderByItem(order , batchOps);
        batchOps.insert(order);
        batchOps.execute();
        return order;
    }

    private void insertOrderByUser(Order order , CassandraBatchOperations batchOps) {
        batchOps.insert(new OrderByUser(new OrderByUser.Key(order.getUserId() , order.getId().getOrderDate() , order.getId().getOrderId())));
    }

    private void insertOrderByCook(Order order , CassandraBatchOperations batchOps) {
        order.getOrderLines().forEach(ol ->
            batchOps.insert( new OrderByCook(new OrderByCook.Key(ol.getCookId() , order.getId().getOrderId() , ol.getItemId() , order.getDeliveryDate() , order.getRecurringInterval()) , menuItemByUserRepository.findByKeyIdAndKeyItemId(ol.getCookId() , ol.getItemId()).getName() , order.getId().getOrderDate() , order.getDeliveryDate() , order.getRecurringInterval()))
        );
    }

    private void insertOrderByItem(Order order , CassandraBatchOperations batchOps) {
        order.getOrderLines().forEach(ol -> {
                final User cook = userRepository.findById(ol.getCookId()).orElseThrow(() -> new RuntimeException("Cannot locate cook by id!"));
                batchOps.insert( new OrderByItem(new OrderByItem.Key(order.getId().getOrderId() , order.getUserId() , order.getId().getOrderDate() , ol.getItemId()) , ol.getCookId() , cook.getFirstName() + " " + cook.getLastName() , cook.getId().getId() , cook.getAddress() ,  menuItemByUserRepository.findByKeyIdAndKeyItemId(ol.getCookId() , ol.getItemId()).getName()));
            }
        );
    }

    @Override
    public <S extends Order> List<S> insert(Iterable<S> entities) {
        List<S> orders = new ArrayList<>();
        entities.forEach(e -> orders.add(insert(e)));
        return orders;
    }

    @Override
    public <S extends Order> S save(S entity) {
        return insert(entity);
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        return insert(entities);
    }


}
