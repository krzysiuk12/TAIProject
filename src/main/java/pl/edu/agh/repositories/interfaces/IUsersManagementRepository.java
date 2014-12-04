package pl.edu.agh.repositories.interfaces;

import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.domain.UserConnection;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementRepository extends IBaseHibernateRepository<UserAccount> {

    public UserAccount getUserAccountByUserId(String userId);

    public UserConnection getUserConnectionById(Long id);

    public UserConnection getUserConnectionByUserId(String userId);

}
