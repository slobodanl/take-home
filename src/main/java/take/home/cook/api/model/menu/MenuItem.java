package take.home.cook.api.model.menu;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table("menu_items")
/**
 *     item_id UUID,
 *     user_id UUID,
 *     item_picture TEXT,
 *     item_name TEXT,
 *     item_description TEXT,
 *     item_added TIMESTAMP,
 *     available INT,
 *     categories set<TEXT>,
 *     remarks set<TEXT>,
 *     tags set<TEXT>,
 *     PRIMARY KEY ((item_id) , user_id , item_added)
 *     WITH CLUSTERING ORDER BY(user_id ASC , item_added DESC)
 */
public class MenuItem {
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name="item_id" , ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private UUID id;

        @PrimaryKeyColumn(name="user_id" , ordinal = 1)
        private UUID userId;

        @PrimaryKeyColumn(name="item_added" , ordinal = 2)
        private Date itemAdded;

        public Key(UUID id, UUID userId, Date itemAdded) {
            this.id = id;
            this.userId = userId;
            this.itemAdded = itemAdded;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public Date getItemAdded() {
            return itemAdded;
        }

        public void setItemAdded(Date itemAdded) {
            this.itemAdded = itemAdded;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", itemAdded=" + itemAdded +
                    '}';
        }
    }

    public MenuItem(Key key, String picture, String name, String description, boolean available, Set<String> categories, Set<String> remarks, Set<String> tags) {
        this.key = key;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.available = available;
        this.categories = categories;
        this.remarks = remarks;
        this.tags = tags;
    }

    @PrimaryKey private Key key;
    @Column("item_picture")
    private String picture;
    @Column("item_name")
    private String name;
    @Column("item_description")
    private String description;
    @Column("available")
    private boolean available;
    @Column("categories")
    private Set<String> categories;
    @Column("remarks")
    private Set<String> remarks;
    @Column("tags")
    private Set<String> tags;


    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<String> getRemarks() {
        return remarks;
    }

    public void setRemarks(Set<String> remarks) {
        this.remarks = remarks;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "key=" + key +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", categories=" + categories +
                ", remarks=" + remarks +
                ", tags=" + tags +
                '}';
    }
}
