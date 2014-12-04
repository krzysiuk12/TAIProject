package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserConnection;
import pl.edu.agh.domain.UserGroup;
import pl.edu.agh.repositories.interfaces.IUsersManagementRepository;
import pl.edu.agh.services.interfaces.IUsersManagementService;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Service
public class UsersManagementService implements IUsersManagementService {

    private IUsersManagementRepository usersManagementRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersManagementService(IUsersManagementRepository usersManagementRepository, PasswordEncoder passwordEncoder) {
        this.usersManagementRepository = usersManagementRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserAccount addNewUser(String userId, String firstName, String lastName, String email, String userName, UserGroup userGroup) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userId);
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setEmail(email);
        userAccount.setUsername(userName);
        userAccount.setUserGroup(userGroup);
        usersManagementRepository.saveOrUpdate(userAccount);
        return userAccount;
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getUserAccountById(Long id) {
        return usersManagementRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getUserAccountByUserId(String userId) {
        return usersManagementRepository.getUserAccountByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserConnection getUserConnectionById(Long id) {
        return usersManagementRepository.getUserConnectionById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserConnection getUserConnectionByUserId(String userId) {
        return usersManagementRepository.getUserConnectionByUserId(userId);
    }
}
