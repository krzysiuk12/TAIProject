package pl.edu.agh.domain;

import javax.persistence.*;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Entity
@Table(name = "USERCONNECTION")
public class UserConnection extends BaseObject {

    private String userId;
    private String providerId;
    private String providerUserId;
    private int rank;
    private String sessionId;
    private String displayName;
    private String profileUrl;
    private String imageUrl;
    private String accessToken;
    private String secret;
    private String refreshToken;
    private Long expireTime;

    public UserConnection() {
    }

    public UserConnection(String userId, String providerId, String providerUserId, int rank, String sessionId, String displayName, String profileUrl, String imageUrl, String accessToken, String accessTokenSecret, String refreshToken, Long expireTime) {
        this.userId = userId;
        this.providerId = providerId;
        this.providerUserId = providerUserId;
        this.rank = rank;
        this.sessionId = sessionId;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.secret = accessTokenSecret;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

/*    @Override
    @Id
    @GeneratedValue(generator = "PK_USERCONNECTIONS", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PK_USERCONNECTIONS", sequenceName = "PK_USERCONNECTIONS", initialValue = 1, allocationSize = 1)
    public Long getId() {
        return super.getId();
    }*/

    @Id
    @Column(name = "USERID", length = 60)
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "PROVIDERID", length = 60)
    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Column(name = "PROVIDERUSERID", length = 60)
    public String getProviderUserId() {
        return providerUserId;
    }
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    @Column(name = "RANK", length = 60)
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Column(name = "DISPLAYNAME", length = 100)
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

    @Column(name = "ACCESSTOKEN", length = 60)
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Column(name = "SECRET", length = 60)
    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
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

    @Column(name = "EXPIRETIME", length = 200)
    public Long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
