package pl.edu.agh.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.domain.Comment;
import pl.edu.agh.domain.Event;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.repositories.interfaces.IEventManagementRepository;
import pl.edu.agh.services.interfaces.IEventsManagementService;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
@Service
public class EventsManagementService implements IEventsManagementService {

    @Autowired
    private IEventManagementRepository eventManagementRepository;
    @Autowired
    private IUsersManagementService usersManagementService;

    @Autowired
    public EventsManagementService(IEventManagementRepository eventManagementRepository) {
        this.eventManagementRepository = eventManagementRepository;
    }

    @Override
    @Transactional
    public void addNewEvent(Event event) {
        eventManagementRepository.saveOrUpdate(event);
    }

    @Override
    @Transactional
    public void addNewComment(Event event, Comment comment) {
        comment.setEvent(event);
        eventManagementRepository.saveOrUpdate(comment);
    }

    @Override
    @Transactional
    public void updateEvent(Event event) {
        Event toUpdateEvent = eventManagementRepository.getById(event.getId());
        toUpdateEvent.setTitle(event.getTitle());
        toUpdateEvent.setDescription(event.getDescription());
        toUpdateEvent.setDate(event.getDate());
        toUpdateEvent.setHashTags(event.getHashTags());
        toUpdateEvent.setUrl(event.getUrl());
        eventManagementRepository.saveOrUpdate(toUpdateEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventManagementRepository.getById(id);
    }

    @Override
    @Transactional
    public void removeEvent(Event event) {
        for(Comment comment : event.getComments()) {
            eventManagementRepository.removeComment(comment);
        }
        eventManagementRepository.removeEvent(event);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllCreatorEvents(UserAccount account) {
        return eventManagementRepository.getAllCreatorEvents(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventManagementRepository.getAllEvents();
    }
}
