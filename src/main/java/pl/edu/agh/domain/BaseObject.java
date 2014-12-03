package pl.edu.agh.domain;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public abstract class BaseObject {

    protected Long id;
    protected int versionNumber;

    public BaseObject() {
    }

    @Transient
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public int getVersionNumber() {
        return versionNumber;
    }
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }
}
