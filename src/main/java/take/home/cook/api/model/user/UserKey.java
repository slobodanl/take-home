package take.home.cook.api.model.user;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@PrimaryKeyClass
public class UserKey {
    @PrimaryKeyColumn(
            name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID id;
    @PrimaryKeyColumn(
            name = "user_type", ordinal = 1)
    private String type;


    public UserKey(UUID id, String type) {
        this.id = id;
        this.type = UserType.byType(type).getType();
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
        return "UserKey{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
