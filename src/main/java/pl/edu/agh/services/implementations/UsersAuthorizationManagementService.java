package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserGroup;
import pl.edu.agh.services.interfaces.IUsersAuthorizationManagementService;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Service
public class UsersAuthorizationManagementService implements IUsersAuthorizationManagementService {

    private IUsersManagementService usersManagementService;

    @Autowired
    public UsersAuthorizationManagementService(IUsersManagementService usersManagementService) {
        this.usersManagementService = usersManagementService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        pl.edu.agh.domain.UserAccount user = usersManagementService.getUserAccountByLogin(login);
        List<GrantedAuthority> authorities = buildAuthorities(user);
        return convertUserAccountToUser(user, authorities);
    }

    private User convertUserAccountToUser(pl.edu.agh.domain.UserAccount userAccount, List<GrantedAuthority> authorities) {
        return new User(userAccount.getLogin(), userAccount.getPassword(), userAccount.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(UserAccount userAccount) {
        Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();
        switch (userAccount.getUserGroup()) {
            case COMMENTER:
                authoritiesSet.add(new SimpleGrantedAuthority(UserGroup.COMMENTER.name()));
                break;
            case CREATOR:
                authoritiesSet.add(new SimpleGrantedAuthority(UserGroup.CREATOR.name()));
                break;
            default:
                authoritiesSet.add(new SimpleGrantedAuthority(UserGroup.COMMENTER.name()));
                authoritiesSet.add(new SimpleGrantedAuthority(UserGroup.CREATOR.name()));

        }
        return new ArrayList(authoritiesSet);
    }
}
