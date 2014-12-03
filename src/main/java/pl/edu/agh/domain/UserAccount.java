package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Entity
@Table(name = "USERACCOUNTS")
public class UserAccount extends BaseObject {

    private String login;
    private String password;
    private boolean enabled;
    private UserGroup userGroup;

    public UserAccount() {
    }

    public UserAccount(String login, UserGroup userGroup) {
        this.login = login;
        this.userGroup = userGroup;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_USERACCOUNTS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_USERACCOUNTS", sequenceName = "PK_USERACCOUNTS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "LOGIN", length = 50, nullable = false)
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PASSWORD", length = 60, nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ENABLED", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "USERGROUP", length = 50, nullable = false)
    public UserGroup getUserGroup() {
        return userGroup;
    }
    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
