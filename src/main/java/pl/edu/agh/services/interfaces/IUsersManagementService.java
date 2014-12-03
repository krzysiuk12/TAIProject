package pl.edu.agh.services.interfaces;

import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserGroup;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementService {

    public void addNewUser(String login, String password, UserGroup userGroup);

    public UserAccount getUserAccountById(Long id);

    public UserAccount getUserAccountByLogin(String login);

}
