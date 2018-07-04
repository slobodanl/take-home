package take.home.cook.api.model.menu;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Table("menu_items_by_user")
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
 *     PRIMARY KEY ((user_id) , item_id , item_added)
 *     WITH CLUSTERING ORDER BY(item_id ASC , item_added DESC)
 */
public class MenuItemByUser {
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(name="user_id" , ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private UUID id;

        @PrimaryKeyColumn(name="item_id" , ordinal = 1)
        private UUID itemId;

        @PrimaryKeyColumn(name="item_added" , ordinal = 2)
        private Date itemAdded;

        public Key(UUID id, UUID itemId, Date itemAdded) {
            this.id = id;
            this.itemId = itemId;
            this.itemAdded = itemAdded;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public UUID getItemId() {
            return itemId;
        }

        public void setItemId(UUID itemId) {
            this.itemId = itemId;
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
                    ", itemId=" + itemId +
                    ", itemAdded=" + itemAdded +
                    '}';
        }
    }

    public MenuItemByUser() {
    }

    public MenuItemByUser(Key key, String picture, String name, String description, boolean available, Set<String> categories, Set<String> remarks, Set<String> tags) {
        this.key = key;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.available = available;
        this.categories = categories;
        this.remarks = remarks;
        this.tags = tags;
    }

    public MenuItemByUser(Key key , MenuItem menuItem) {
        this.key = key;
        this.picture = menuItem.getPicture();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.available = menuItem.isAvailable();
        this.categories = menuItem.getCategories();
        this.remarks = menuItem.getRemarks();
        this.tags = menuItem.getTags();
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
        return "MenuItemByUser{" +
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
