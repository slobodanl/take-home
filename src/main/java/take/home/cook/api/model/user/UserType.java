package take.home.cook.api.model.user;

import java.util.Objects;
import java.util.stream.Stream;

public enum UserType {
    CUSTOMER("customer"),COOK("cook") , DELIVERY("delivery");
    private String type;

    UserType(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public static UserType byType(String type) {
        return Stream.of(UserType.values()).filter(t -> Objects.equals(type , t.type )).findFirst().orElseThrow( () -> new RuntimeException("Not matched"));
    }
}
