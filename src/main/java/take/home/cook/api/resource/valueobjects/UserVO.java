package take.home.cook.api.resource.valueobjects;

import take.home.cook.api.model.user.User;
import take.home.cook.api.model.user.UserByType;

import java.util.UUID;

public class UserVO {

    public UserVO(User user) {
        this.id = user.getId().getId();
        this.type = user.getId().getType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }

    public UserVO(UserByType user) {
        this.id = user.getId().getId();
        this.type = user.getId().getType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
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

    private UUID id;
    private String type;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;


}
