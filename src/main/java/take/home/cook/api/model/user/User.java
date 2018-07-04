package take.home.cook.api.model.user;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user")
public class User {

    @PrimaryKey UserKey userKey;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column
    private String address;

    @Column
    private String phone;

    public User(UserKey userKey, String firstName, String lastName, String address, String phone) {
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

    public UserKey getId() {
        return userKey;
    }

    public void setId(UserKey userKey) {
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