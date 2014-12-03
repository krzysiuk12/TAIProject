package pl.edu.agh.repositories.implementations;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.repositories.interfaces.IUsersManagementRepository;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Repository
public class UsersManagementRepository extends BaseHibernateRepository implements IUsersManagementRepository {

    @Autowired
    public UsersManagementRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public UserAccount getById(Long id) {
        return (UserAccount) getCurrentSession().get(UserAccount.class, id);
    }

    @Override
    public UserAccount getUserAccountByLogin(String name) {
        return (UserAccount)getCurrentSession().createCriteria(UserAccount.class).add(Restrictions.eq("login", name)).list().get(0);
    }
}
