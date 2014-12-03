package pl.edu.agh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Configuration
@EnableSocial
@PropertySource({"classpath:twitter.properties"})
public class SpringSocialConfig implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ConnectionSignUp connectionSignUp;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        TwitterConnectionFactory twitterConnectionFactory = new TwitterConnectionFactory(environment.getProperty("twitter.customerKey"), environment.getProperty("twitter.customerSecret"));
        connectionFactoryConfigurer.addConnectionFactory(twitterConnectionFactory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(connectionSignUp);
        return null;
    }

}
