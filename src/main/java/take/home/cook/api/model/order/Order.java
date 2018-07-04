package take.home.cook.api.model.order;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Date;
import java.util.UUID;

@Table("orders")
/**
 *     order_id UUID,
 *     user_id UUID,
 *     order_date TIMESTAMP,
 *     delivery_address text,
 *     delivery_phone text,
 *     PRIMARY KEY ((order_id) , order_date)
 *     WITH CLUSTERING ORDER BY(order_date DESC)
 */
public class Order {
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name="order_id" , ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private UUID orderId;

        @PrimaryKeyColumn(name="order_date" , ordinal = 1)
        private Date orderDate;

        public UUID getOrderId() {
            return orderId;
        }

        public void setOrderId(UUID orderId) {
            this.orderId = orderId;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "orderId=" + orderId +
                    ", orderDate=" + orderDate +
                    '}';
        }
    }
    @PrimaryKey
    private Key id;

    @Column("user_id")
    private UUID userId;

    @Column("delivery_address")
    private String deliveryAddress;

    @Column("delivery_phone")
    private String deliveryPhone;


    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryPhone='" + deliveryPhone + '\'' +
                '}';
    }
}
