package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Entity
@Table(name = "USERACCOUNTS")
public class UserAccount extends BaseObject {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private UserGroup userGroup;

    public UserAccount() {
    }

    public UserAccount(String firstName, String lastName, String email, String username, UserGroup userGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.userGroup = userGroup;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_USERACCOUNTS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_USERACCOUNTS", sequenceName = "PK_USERACCOUNTS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "USERID", length = 100)
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "FIRSTNAME", length = 500)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LASTNAME", length = 500)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "USERNAME", length = 100)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "USERGROUP", length = 50)
    public UserGroup getUserGroup() {
        return userGroup;
    }
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
