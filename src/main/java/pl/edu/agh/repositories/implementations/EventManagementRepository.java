package pl.edu.agh.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.domain.Event;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.repositories.interfaces.IEventManagementRepository;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Repository
public class EventManagementRepository extends BaseHibernateRepository implements IEventManagementRepository {

    @Autowired
    public EventManagementRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    @Transactional
    public List<Event> getAllCreatorEvents(UserAccount creator) {
        Criteria criteria = getCurrentSession().createCriteria(Event.class);
        criteria.add(Restrictions.eq("creator", creator));
        return criteria.list();
    }

    @Override
    @Transactional
    public List<Event> getAllEvents() {
        Criteria criteria = getCurrentSession().createCriteria(Event.class);
        return criteria.list();
    }

    @Override
    @Transactional
    public void saveOrUpdate(Object entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public Event getById(Long id) {

        return (Event) getCurrentSession().get(Event.class, id);
    }

    @Override
    @Transactional
    public void removeEvent(Event event) {
        getCurrentSession().delete(event);
    }

}
