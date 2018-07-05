package take.home.cook.api.model.order;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("orders_by_user")
/**
 *     user_id UUID,
 *     order_id UUID,
 *     order_date TIMESTAMP,
 *     PRIMARY KEY ((user_id) , order_date , order_id)
 *     WITH CLUSTERING ORDER BY(order_date DESC , order_id DESC)
 */
public class OrderByUser {
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name="user_id" , ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private UUID userId;

        @PrimaryKeyColumn(name="order_date" , ordinal = 1)
        private Date orderDate;

        @PrimaryKeyColumn(name="order_id" , ordinal = 2)
        private UUID orderId;


        public Key(UUID userId, Date orderDate, UUID orderId) {
            this.userId = userId;
            this.orderDate = orderDate;
            this.orderId = orderId;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        public UUID getOrderId() {
            return orderId;
        }

        public void setOrderId(UUID orderId) {
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "userId=" + userId +
                    ", orderDate=" + orderDate +
                    ", orderId=" + orderId +
                    '}';
        }
    }

    public OrderByUser(Key id) {
        this.id = id;
    }

    @PrimaryKey
    private Key id;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "OrderByUser{" +
                "id=" + id +
                '}';
    }
}
