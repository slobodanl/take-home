package take.home.cook.api.model.user.domain;

import org.springframework.data.cassandra.core.mapping.Column;

public class UserBase {
    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column
    private String address;

    @Column
    private String phone;
}
