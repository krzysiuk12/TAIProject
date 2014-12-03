package pl.edu.agh.repositories.interfaces;

import pl.edu.agh.domain.UserAccount;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IUsersManagementRepository extends IBaseHibernateRepository<UserAccount> {

    public UserAccount getUserAccountByLogin(String name);

}
