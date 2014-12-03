package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Entity
@Table(name = "USERCONNECTIONS")
public class UserConnection extends BaseObject {

    private UserAccount userAccount;
    private String providerId;
    private String sessionId;
    private String displayName;
    private String profileUrl;
    private String imageUrl;
    private String accessToken;
    private String accessTokenSecret;
    private String refreshToken;

    public UserConnection() {
    }

    public UserConnection(UserAccount userAccount, String providerId, String sessionId, String displayName, String profileUrl, String imageUrl, String accessToken, String accessTokenSecret, String refreshToken) {
        this.userAccount = userAccount;
        this.providerId = providerId;
        this.sessionId = sessionId;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.refreshToken = refreshToken;
    }

    @Override
    @Id
    @GeneratedValue(generator = "PK_USERCONNECTIONS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_USERCONNECTIONS", sequenceName = "PK_USERCONNECTIONS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USERACCOUNT", foreignKey = @ForeignKey(name = "FK_USERCONNECTION_USERACCOUNT_USER"), nullable = false)
    public UserAccount getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Column(name = "PROVIDERID", length = 60, nullable = false)
    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Column(name = "DISPLAYNAME", length = 100, nullable = false)
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "PROFILEURL", length = 200)
    public String getProfileUrl() {
        return profileUrl;
    }
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Column(name = "ACCESSTOKEN", length = 60, nullable = false)
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Column(name = "ACCESSTOKENSECRET", length = 60, nullable = false)
    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }
    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    @Column(name = "REFRESHTOKEN", length = 60)
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column(name = "SESSIONID", length = 60)
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "IMAGEURL", length = 200)
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
