package pl.edu.agh.services.interfaces;

import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserConnection;
import pl.edu.agh.domain.UserGroup;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementService {

    public UserAccount addNewUser(String userId, String firstName, String lastName, String email, String userName, UserGroup userGroup);

    public UserAccount getUserAccountById(Long id);

    public UserAccount getUserAccountByUserId(String userId);

    public UserConnection getUserConnectionById(Long id);

    public UserConnection getUserConnectionByUserId(String userId);
}
