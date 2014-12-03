package pl.edu.agh.repositories.interfaces;

import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserConnection;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementRepository extends IBaseHibernateRepository<UserAccount> {

    public UserAccount getUserAccountByUserName(String userName);

    public UserConnection getUserConnectionById(Long id);

    public UserConnection getUserConnectionByAccount(UserAccount userAccount);

}
