package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-12-04.
 */
@Entity
@Table(name = "AUTHORITIES")
public class Authority extends BaseObject {

    private String username;
    private String authority;

    public Authority() {
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_AUTHORITIES", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_AUTHORITIES", sequenceName = "PK_AUTHORITIES", initialValue = 1, allocationSize = 1)
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

    @Column(name = "AUTHORITY", length = 60)
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
