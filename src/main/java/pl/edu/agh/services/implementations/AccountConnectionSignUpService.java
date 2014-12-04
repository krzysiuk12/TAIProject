package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserGroup;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.UUID;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Service
public class AccountConnectionSignUpService implements ConnectionSignUp {

    private IUsersManagementService usersManagementService;

    @Autowired
    public AccountConnectionSignUpService(IUsersManagementService usersManagementService) {
        this.usersManagementService = usersManagementService;
    }

    @Override
    public String execute(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        UserAccount userAccount = usersManagementService.addNewUser(UUID.randomUUID().toString(), userProfile.getFirstName(), userProfile.getLastName(), userProfile.getEmail(), userProfile.getUsername(), UserGroup.BOTH);
        return userAccount.getUserId();
    }
}
