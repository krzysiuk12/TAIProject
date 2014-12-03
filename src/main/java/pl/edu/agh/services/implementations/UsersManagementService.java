package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.domain.UserAccount;
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
    public void addNewUser(String login, String password, UserGroup userGroup) {
        UserAccount userAccount = new UserAccount();
        userAccount.setLogin(login);
        userAccount.setPassword(passwordEncoder.encode(password));
        userAccount.setEnabled(true);
        userAccount.setUserGroup(userGroup);
        usersManagementRepository.saveOrUpdate(userAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getUserAccountById(Long id) {
        return usersManagementRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getUserAccountByLogin(String login) {
        return usersManagementRepository.getUserAccountByLogin(login);
    }

    public IUsersManagementRepository getUsersManagementRepository() {
        return usersManagementRepository;
    }

    public void setUsersManagementRepository(IUsersManagementRepository usersManagementRepository) {
        this.usersManagementRepository = usersManagementRepository;
    }

}
