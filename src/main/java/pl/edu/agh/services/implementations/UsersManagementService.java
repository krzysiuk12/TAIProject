package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.domain.*;
import pl.edu.agh.repositories.interfaces.IUsersManagementRepository;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import javax.servlet.http.HttpServletRequest;

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
    public UserAccount addNewUserAccount(String userId, String firstName, String lastName, String email, String userName, UserGroup userGroup) {
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

    @Override
    @Transactional
    public User addNewUser(String username, String password, boolean enabled) {
        User user = new User(username, password, enabled);
        usersManagementRepository.saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional
    public Authority addNewAuthority(String username, String authority) {
        Authority authorityResult = new Authority(username, authority);
        usersManagementRepository.saveOrUpdate(authorityResult);
        return authorityResult;
    }

    @Override
    @Transactional
    public UserRole addNewUserRole(String username, String role) {
        UserRole userRole = new UserRole(username, role);
        usersManagementRepository.saveOrUpdate(userRole);
        return userRole;
    }

    @Override
    @Transactional
    public UserAccount getCurrentUser(HttpServletRequest request) {
        SocialUser socialUser = (SocialUser) ((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal();
        UserAccount userAccount = getUserAccountByUserId(socialUser.getUsername());
        return userAccount;
    }
}
