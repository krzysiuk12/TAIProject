package pl.edu.agh.repositories.interfaces;

import pl.edu.agh.domain.Comment;
import pl.edu.agh.domain.Event;
import pl.edu.agh.domain.UserAccount;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IEventManagementRepository extends IBaseHibernateRepository<Event> {

    public List<Event> getAllCreatorEvents(UserAccount creator);
    public List<Event> getAllEvents();
    public void removeEvent(Event event);
    public void removeComment(Comment comment);

}
