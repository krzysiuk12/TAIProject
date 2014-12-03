package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.Model;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserConnection;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
public class SocialContollerUtils {

    public static final String MODEL_USER_CONNECTION = "userConnection";
    public static final String MODEL_USER_ACCOUNT = "userAccount";
    public static final String SESSION_USER_CONNECTION = "SESSION_USER_CONNECTION";
    public static final String SESSION_USER_ACCOUNT = "SESSION_USER_ACCOUNT";

    @Autowired
    private IUsersManagementService usersManagementService;

    public void setModel(HttpServletRequest request, Principal currentUser, Model model) {
        Long id = currentUser != null ? null : Long.parseLong(currentUser.getName());
        String path = request.getRequestURI();
        HttpSession session = request.getSession();

        UserConnection connection = null;
        UserAccount account = null;

        if(id != null) {
            account = getUserAccount(session, id);
            connection = getUserConnection(session, account);
        }

        Throwable exception = (Throwable)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        model.addAttribute("exception", exception == null ? null : exception.getMessage());
        model.addAttribute(MODEL_USER_ACCOUNT, account);
        model.addAttribute(MODEL_USER_CONNECTION, connection);

    }

    private UserAccount getUserAccount(HttpSession session, Long id) {
        UserAccount account = (UserAccount) session.getAttribute(SESSION_USER_ACCOUNT);
        if(account == null || (account != null && !account.getId().equals(id))) {
            account = usersManagementService.getUserAccountById(id);
            session.setAttribute(SESSION_USER_ACCOUNT, account);
        }
        return account;
    }

    private UserConnection getUserConnection(HttpSession session, UserAccount userAccount) {
        UserConnection connection = (UserConnection) session.getAttribute(SESSION_USER_CONNECTION);
        if(connection == null || (userAccount != null && !connection.getUserAccount().getId().equals(userAccount.getId()))) {
            connection = usersManagementService.getUserConnectionByAccount(userAccount);
            session.setAttribute(SESSION_USER_CONNECTION, connection);
        }
        return connection;
    }

}
