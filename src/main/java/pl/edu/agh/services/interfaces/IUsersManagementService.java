package pl.edu.agh.services.interfaces;

import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserConnection;
import pl.edu.agh.domain.UserGroup;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementService {

    public UserAccount addNewUser(String firstName, String lastName, String email, String userName, UserGroup userGroup);

    public UserAccount getUserAccountById(Long id);

    public UserAccount getUserAccountByLogin(String login);

    public UserConnection getUserConnectionById(Long id);

    public UserConnection getUserConnectionByAccount(UserAccount userAccount);
}
