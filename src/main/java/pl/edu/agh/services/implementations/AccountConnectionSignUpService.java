package pl.edu.agh.services.implementations;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import pl.edu.agh.domain.*;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.UUID;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
public class AccountConnectionSignUpService implements ConnectionSignUp {

    private IUsersManagementService usersManagementService;

    public AccountConnectionSignUpService(IUsersManagementService usersManagementService) {
        this.usersManagementService = usersManagementService;
    }

    @Override
    public String execute(Connection<?> connection) {
        String userId = UUID.randomUUID().toString();
        UserProfile userProfile = connection.fetchUserProfile();
        UserAccount userAccount = usersManagementService.addNewUserAccount(userId, userProfile.getFirstName(), userProfile.getLastName(), userProfile.getEmail(), userProfile.getUsername(), UserGroup.BOTH);
        User user = usersManagementService.addNewUser(userId, "ABCABCAB", true);
        Authority authority = usersManagementService.addNewAuthority(userId, "USER");
        UserRole userRole = usersManagementService.addNewUserRole(userId, "USER");
        return userId;
    }
}
