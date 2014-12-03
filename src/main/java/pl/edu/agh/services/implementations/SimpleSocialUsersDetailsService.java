package pl.edu.agh.services.implementations;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import pl.edu.agh.services.interfaces.ISocialUsersDetailsManagementService;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
public class SimpleSocialUsersDetailsService implements ISocialUsersDetailsManagementService {

    private UserDetailsService userDetailsService;

    public SimpleSocialUsersDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}
