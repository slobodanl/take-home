package take.home.cook.api.resource.valueobjects;


import com.fasterxml.jackson.annotation.JsonIgnore;
import take.home.cook.api.model.user.User;
import take.home.cook.api.model.user.UserByType;
import take.home.cook.api.resource.utils.VOUtils;

import java.util.UUID;

public class UserVO {
    public UserVO() {
    }

    public UserVO(User user) {
        VOUtils.copyProperties(this , user);
        this.id = user.getId().getId();
        this.type = user.getId().getType();
    }

    public UserVO(UserByType user) {
        VOUtils.copyProperties(this , user);
        this.id = user.getId().getId();
        this.type = user.getId().getType();
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

    @JsonIgnore
    public User getEntity() {
        return new User(new User.Key(this.id , this.type) , this.firstName , this.lastName , this.address , this.phone);
    }

}
