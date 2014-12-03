package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Entity
@Table(name = "COMMENTS")
public class Comment extends BaseObject {

    private Event event;
    private UserAccount commenter;
    private Rating rating;
    private String comment;
    private boolean privateComment;

    public Comment() {
    }

    public Comment(Event event, UserAccount commenter, Rating rating, String comment, boolean privateComment) {
        this.event = event;
        this.commenter = commenter;
        this.rating = rating;
        this.comment = comment;
        this.privateComment = privateComment;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_COMMENTS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_COMMENTS", sequenceName = "PK_COMMENTS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "ID_EVENT", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENT_EVENT_EVENT"))
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    @ManyToOne
    @JoinColumn(name = "ID_COMMENTER", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENT_USERACCOUNT_COMMENTER"))
    public UserAccount getCommenter() {
        return commenter;
    }
    public void setCommenter(UserAccount commenter) {
        this.commenter = commenter;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "RATING", length = 50, nullable = false)
    public Rating getRating() {
        return rating;
    }
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Column(name = "COMMENT", length = 1000, nullable = false)
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "ISPRIVATE", nullable = false)
    public boolean isPrivateComment() {
        return privateComment;
    }
    public void setPrivateComment(boolean privateComment) {
        this.privateComment = privateComment;
    }
}
