package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import pl.edu.agh.services.interfaces.ITwitterService;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Service
@PropertySource({"classpath:twitter.properties"})
public class TwitterService implements ITwitterService {

    @Autowired
    private Environment environment;
    private Twitter twitterTemplate;

    public Twitter getTwitterTemplate() {
        if(twitterTemplate == null) {
            twitterTemplate = new TwitterTemplate(getCustomerKey(), getCustomerSecret());
        }
        return twitterTemplate;
    }

    //<editor-fold desc="Twitter Connectivity Properties">
    public String getCustomerKey() {
        return environment.getProperty("twitter.customerKey");
    }

    public String getCustomerSecret() {
        return environment.getProperty("twitter.customerSecret");
    }


    public String getAccessToken() {
        return environment.getProperty("twitter.accessToken");
    }

    public String getAccessTokenSecret() {
        return environment.getProperty("twitter.accessTokenSecret");
    }
    //</editor-fold>

    //<editor-fold desc="Getters And Setters">
    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    //</editor-fold>
}
