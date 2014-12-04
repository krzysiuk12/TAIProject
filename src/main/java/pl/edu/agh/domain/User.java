package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-12-04.
 */
@Entity
@Table(name = "USERS")
public class User extends BaseObject {

    private String username;
    private String password;
    private boolean enabled;

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_USERS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_USERS", sequenceName = "PK_USERS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "USERNAME", length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ENABLED", length = 100)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
