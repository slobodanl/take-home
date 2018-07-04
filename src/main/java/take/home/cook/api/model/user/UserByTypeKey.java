package take.home.cook.api.model.user;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@PrimaryKeyClass
public class UserByTypeKey {
    @PrimaryKeyColumn(
            name = "user_type", ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
    private String type;

    @PrimaryKeyColumn(
            name = "user_id", ordinal = 1)
    private UUID id;


    public UserByTypeKey(String type , UUID id) {
        this.type = UserType.byType(type).getType();
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserType getUserType() {
        return UserType.byType(type);
    }

    @Override
    public String toString() {
        return "UserByTypeKey{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
