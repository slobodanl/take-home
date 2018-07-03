package take.home.cook.api.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class BaseEntity<ID> {

    @PrimaryKey  private ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
