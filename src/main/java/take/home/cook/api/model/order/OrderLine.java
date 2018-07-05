package take.home.cook.api.model.order;

import java.util.UUID;

public class OrderLine {
    private UUID itemId;
    private UUID cookId;
    private Long qty;

    public OrderLine(UUID itemId, UUID cookId, Long qty) {
        this.itemId = itemId;
        this.cookId = cookId;
        this.qty = qty;
    }

    public UUID getItemId() {
        return itemId;
    }

    public UUID getCookId() {
        return cookId;
    }

    public Long getQty() {
        return qty;
    }
}
