package pl.edu.agh.repositories.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public class BaseHibernateRepository {

    private SessionFactory sessionFactory;

    public BaseHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }
}
