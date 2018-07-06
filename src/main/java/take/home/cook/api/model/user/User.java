package take.home.cook.api.model.user;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Table("user")
public class User {
    @PrimaryKeyClass
    public static class Key {
        @PrimaryKeyColumn(
                name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        private UUID id;
        @PrimaryKeyColumn(
                name = "user_type", ordinal = 1)
        private String type;


        public Key(UUID id, String type) {
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

    @PrimaryKey Key userKey;



    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column
    private String address;

    @Column
    private String phone;

    public User(Key userKey, String firstName, String lastName, String address, String phone) {
        this.userKey = userKey;
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
        return userKey;
    }

    public void setId(Key userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userKey +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}