package pl.edu.agh.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.agh.config.ApplicationConfig;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserGroup;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Krzysztof Kicinger on 2014-12-06.
 */
@ContextConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UsersManagementServiceTests {

    @Autowired
    private IUsersManagementService usersManagementService;

    @Test
    public void saveUserAccountTest() throws Exception {
        usersManagementService.addNewUserAccount("JanKowalskiId", "Jan", "Kowalski", "jan@email.com", "JanKowalski", UserGroup.BOTH);
    }

    @Test
    public void getUserAccountTest() throws Exception {
        String userId = UUID.randomUUID().toString();
        UserAccount userAccount = usersManagementService.addNewUserAccount(userId, "Jan", "Kowalski", "jan@email.com", "JanKowalski", UserGroup.BOTH);
        UserAccount test1 = usersManagementService.getUserAccountById(userAccount.getId());
        UserAccount test2 = usersManagementService.getUserAccountByUserId(userAccount.getUserId());
        assertNotNull(test1);
        assertNotNull(test2);
        assertEquals(userAccount.getId(), test1.getId());
        assertEquals(userAccount.getId(), test2.getId());
        assertEquals(userAccount.getUserId(), test1.getUserId());
        assertEquals(userAccount.getUserId(), test2.getUserId());
    }

    @Test
    public void saveAuthorityTest() throws Exception {
        usersManagementService.addNewAuthority("Username", "USER");
    }

    @Test
    public void saveUserTest() throws Exception {
        usersManagementService.addNewUser("Username", "Password", true);
    }

    @Test
    public void saveUserRoleTest() throws Exception {
        usersManagementService.addNewUserRole("Username", "USER");
    }
}
