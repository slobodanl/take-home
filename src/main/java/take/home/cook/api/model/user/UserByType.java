package take.home.cook.api.model.user;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("user_by_type")
//TODO Add rating column here !
public class UserByType {

    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(
                name = "user_type", ordinal = 0 , type = PrimaryKeyType.PARTITIONED)
        private String type;

        @PrimaryKeyColumn(
                name = "user_id", ordinal = 1)
        private UUID id;


        public Key(String type , UUID id) {
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

    @PrimaryKey Key userByTypeKey;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column
    private String address;

    @Column
    private String phone;

    public UserByType(Key userByTypeKey, String firstName, String lastName, String address, String phone) {
        this.userByTypeKey = userByTypeKey;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Key getId() {
        return userByTypeKey;
    }

    public void setId(Key userByTypeKey) {
        this.userByTypeKey = userByTypeKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userByTypeKey +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}