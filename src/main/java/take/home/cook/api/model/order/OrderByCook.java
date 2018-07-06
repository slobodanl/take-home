package take.home.cook.api.model.order;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Table("orders_by_cook")
/**
 *     cook_id UUID,
 *     order_id UUID,
 *     item_id UUID,
 *     item_name TEXT,
 *     order_date TIMESTAMP,
 *     delivery_date TIMESTAMP,
 *     recurring_interval INT,
 *     next_delivery_date TIMESTAMP,
 *     PRIMARY KEY ((cook_id , order_id) , next_delivery_date , item_id )
 *     WITH CLUSTERING ORDER BY(next_delivery_date DESC)
 */
public class OrderByCook {
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name="cook_id" , ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private UUID userId;

        @PrimaryKeyColumn(name="order_id" , ordinal = 1 , type = PrimaryKeyType.PARTITIONED)
        private UUID orderId;

        @PrimaryKeyColumn(name="next_delivery_date" , ordinal = 2)
        private Date nextDeliveryDate;

        @PrimaryKeyColumn(name="item_id" , ordinal = 3)
        private UUID itemId;


        public Key(UUID userId, UUID orderId,  UUID itemId) {
            this.userId = userId;
            this.orderId = orderId;
            this.itemId = itemId;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public UUID getOrderId() {
            return orderId;
        }

        public void setOrderId(UUID orderId) {
            this.orderId = orderId;
        }

        public Date getNextDeliveryDate() {
            return nextDeliveryDate;
        }

        public void setNextDeliveryDate(Date nextDeliveryDate) {
            this.nextDeliveryDate = nextDeliveryDate;
        }

        public UUID getItemId() {
            return itemId;
        }

        public void setItemId(UUID itemId) {
            this.itemId = itemId;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "userId=" + userId +
                    ", orderId=" + orderId +
                    ", nextDeliveryDate=" + nextDeliveryDate +
                    ", itemId=" + itemId +
                    '}';
        }
    }

    public OrderByCook(Key id, String itemName, Date orderDate, Date deliveryDate, Integer recurringInterval) {
        this.id = id;
        this.itemName = itemName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        if (recurringInterval != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(deliveryDate);
            calendar.add(Calendar.DATE , recurringInterval );
            this.id.nextDeliveryDate = calendar.getTime();
        } else {
            this.id.nextDeliveryDate = deliveryDate;
        }
        this.recurringInterval = recurringInterval;
    }

    @PrimaryKey
    private Key id;

    @Column("item_name")
    private String itemName;

    @Column("order_date")
    private Date orderDate;

    @Column("delivery_date")
    private Date deliveryDate;

    @Column("recurring_interval")
    private Integer recurringInterval;



    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getRecurringInterval() {
        return recurringInterval;
    }

    public void setRecurringInterval(Integer recurringInterval) {
        this.recurringInterval = recurringInterval;
    }


    @Override
    public String toString() {
        return "OrderByCook{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", recurringInterval=" + recurringInterval +
                '}';
    }
}
