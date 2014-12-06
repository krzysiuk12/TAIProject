package pl.edu.agh.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Entity
@Table(name = "EVENTS")
public class Event extends BaseObject {

    public enum Status {
        ACTIVE,
        REMOVED
    }

    private UserAccount creator;
    private String title;
    private String description;
    private Date date;
    private String url;
    private Set<String> hashTags;
    private List<Comment> comments;

    public Event() {
    }

    public Event(UserAccount creator, String title, String description, Date date, String url, Set<String> hashTags, List<Comment> comments) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.date = date;
        this.url = url;
        this.hashTags = hashTags;
        this.comments = comments;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_EVENTS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_EVENTS", sequenceName = "PK_EVENTS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "ID_CREATOR", nullable = false, foreignKey = @ForeignKey(name = "FK_EVENT_USER_ACCOUNT_CREATOR"))
    public UserAccount getCreator() {
        return creator;
    }
    public void setCreator(UserAccount creator) {
        this.creator = creator;
    }

    @Column(name = "TITLE", length = 50, nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "DESCRIPTION", length = 500)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE", nullable = false)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "URL", nullable = false)
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "HASHTAGS", joinColumns = @JoinColumn(name = "ID_EVENT"), foreignKey = @ForeignKey(name = "FK_EVENT_HASHTAGS_HASHTAG"))
    @Column(name = "HASHTAG")
    public Set<String> getHashTags() {
        return hashTags;
    }
    public void setHashTags(Set<String> hashTags) {
        this.hashTags = hashTags;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event")
    @Fetch(FetchMode.SELECT)
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Transient
    public String getEventRating() {

        double rating = 0.0;
        for(Comment comment : getComments()) {
            rating += comment.getRating().getValue();
        }
        return new DecimalFormat("0.00").format(getComments().size() > 0 ? rating / getComments().size() : 0.0);
    }

    @Transient
    public String getHashtagsString(String separator) {
        if (getHashTags().size() == 0) {
            return "";
        }

        String hashtags = "";
        for (String hashtag : getHashTags()) {
            hashtags += hashtag + separator;
        }
        return hashtags.substring(0, hashtags.length() - separator.length());
    }
}
