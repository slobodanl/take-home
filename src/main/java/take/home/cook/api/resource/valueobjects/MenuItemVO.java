package take.home.cook.api.resource.valueobjects;

import take.home.cook.api.model.menu.MenuItem;
import take.home.cook.api.model.menu.MenuItemByCategory;
import take.home.cook.api.resource.utils.VOUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class MenuItemVO {

    public MenuItemVO() {
    }

    public MenuItemVO(MenuItem menuItem) {
        VOUtils.copyProperties(this, menuItem);
        this.itemId = menuItem.getKey().getId();
        this.userId = menuItem.getKey().getUserId();
        this.added = menuItem.getKey().getItemAdded();
    }

    public MenuItemVO(MenuItemByCategory menuItemByCategory) {
        VOUtils.copyProperties(this, menuItemByCategory);
        this.itemId = menuItemByCategory.getKey().getItemId();
        this.userId = menuItemByCategory.getKey().getUserId();
        this.added = menuItemByCategory.getKey().getItemAdded();
        this.categories = Collections.singleton(menuItemByCategory.getKey().getId());
    }

    public UUID getItemId() {
        return itemId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Date getAdded() {
        return added;
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

    private UUID itemId;
    private UUID userId;
    private Date added;
    private String picture;
    private String name;
    private String description;
    private boolean available;
    private Set<String> categories;
    private Set<String> remarks;
    private Set<String> tags;



}
