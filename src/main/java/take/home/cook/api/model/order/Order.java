package take.home.cook.api.model.order;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table("orders")
/**
 *     order_id UUID,
 *     user_id UUID,
 *     order_date TIMESTAMP,
 *     delivery_date TIMESTAMP,
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

        public Key(UUID orderId, Date orderDate) {
            this.orderId = orderId;
            this.orderDate = orderDate;
        }

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

    @Column("delivery_date")
    private Date deliveryDate;

    @Column("delivery_address")
    private String deliveryAddress;

    @Column("delivery_phone")
    private String deliveryPhone;

    @Transient
    private Integer recurringInterval;

    @Transient
    private List<OrderLine> orderLines;

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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Integer getRecurringInterval() {
        return recurringInterval;
    }

    public void setRecurringInterval(Integer recurringInterval) {
        this.recurringInterval = recurringInterval;
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
