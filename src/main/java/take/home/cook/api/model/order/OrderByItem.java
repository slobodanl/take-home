package take.home.cook.api.model.order;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Table("order_items")
/**
 *     order_id UUID,
 *     user_id UUID,
 *     order_date TIMESTAMP,
 *     cook_id UUID,
 *     cook_name TEXT,
 *     cook_location_id UUID,
 *     cook_location TEXT,
 *     item_ordered UUID,
 *     item_name TEXT,
 *     PRIMARY KEY ((order_id ) , user_id , order_date , item_ordered)
 *     WITH CLUSTERING ORDER BY(order_date DESC)
 */
public class OrderByItem {
    @PrimaryKeyClass
    public static class Key {

        @PrimaryKeyColumn(name="order_id" , ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private UUID orderId;

        @PrimaryKeyColumn(name="user_id" , ordinal = 1)
        private UUID userId;

        @PrimaryKeyColumn(name="order_date" , ordinal = 2)
        private Date orderDate;

        @PrimaryKeyColumn(name="item_ordered" , ordinal = 3)
        private UUID itemId;

        public Key(UUID orderId, UUID userId, Date orderDate, UUID itemId) {
            this.orderId = orderId;
            this.userId = userId;
            this.orderDate = orderDate;
            this.itemId = itemId;
        }

        public UUID getOrderId() {
            return orderId;
        }

        public void setOrderId(UUID orderId) {
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

        public UUID getItemId() {
            return itemId;
        }

        public void setItemId(UUID itemId) {
            this.itemId = itemId;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "orderId=" + orderId +
                    ", userId=" + userId +
                    ", orderDate=" + orderDate +
                    ", itemId=" + itemId +
                    '}';
        }
    }

    public OrderByItem(Key id, UUID cookId, String cookName, UUID cookLocationId, String cookLocation, String itemName) {
        this.id = id;
        this.cookId = cookId;
        this.cookName = cookName;
        this.cookLocationId = cookLocationId;
        this.cookLocation = cookLocation;
        this.itemName = itemName;
    }

    @PrimaryKey
    private Key id;

    @Column("cook_id")
    private UUID cookId;

    @Column("cook_name")
    private String cookName;

    @Column("cook_location_id")
    private UUID cookLocationId;

    @Column("cook_location")
    private String cookLocation;

    @Column("item_name")
    private String itemName;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public UUID getCookId() {
        return cookId;
    }

    public void setCookId(UUID cookId) {
        this.cookId = cookId;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public UUID getCookLocationId() {
        return cookLocationId;
    }

    public void setCookLocationId(UUID cookLocationId) {
        this.cookLocationId = cookLocationId;
    }

    public String getCookLocation() {
        return cookLocation;
    }

    public void setCookLocation(String cookLocation) {
        this.cookLocation = cookLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "OrderByItem{" +
                "id=" + id +
                ", cookId=" + cookId +
                ", cookName='" + cookName + '\'' +
                ", cookLocationId=" + cookLocationId +
                ", cookLocation='" + cookLocation + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
