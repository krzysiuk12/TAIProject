package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-12-04.
 */
@Entity
@Table(name = "USER_ROLES")
public class UserRole extends BaseObject {

    private String username;
    private String role;

    public UserRole() {
    }

    public UserRole(String username, String role) {
        this.username = username;
        this.role = role;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_USERROLES", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_USERROLES", sequenceName = "PK_USERROLES", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "USERNAME", length = 60)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "ROLE", length = 60)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
