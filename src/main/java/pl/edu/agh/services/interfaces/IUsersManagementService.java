package pl.edu.agh.services.interfaces;

import pl.edu.agh.domain.*;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementService {

    public UserAccount addNewUserAccount(String userId, String firstName, String lastName, String email, String userName, UserGroup userGroup);

    public UserAccount getUserAccountById(Long id);

    public UserAccount getUserAccountByUserId(String userId);

    public UserConnection getUserConnectionById(Long id);

    public UserConnection getUserConnectionByUserId(String userId);

    public User addNewUser(String username, String password, boolean enabled);

    public Authority addNewAuthority(String username, String authority);

    public UserRole addNewUserRole(String username, String role);
}
