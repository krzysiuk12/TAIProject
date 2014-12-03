package pl.edu.agh.services.interfaces;

import pl.edu.agh.domain.Comment;
import pl.edu.agh.domain.Event;
import pl.edu.agh.domain.UserAccount;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public interface IEventsManagementService {

    public void addNewEvent(Event event);

    public void addNewComment(Event event, Comment comment);

    public Event getEventById(Long id);

    public List<Event> getAllCreatorEvents(UserAccount account);

    public List<Event> getAllEvents();

    public void removeEvent(Event event);

    public void updateEvent(Event event);

    public List<Comment> getEventComments(Event event);

    public void publishComment(Event event, Comment comment);
}
