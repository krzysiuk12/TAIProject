package pl.edu.agh.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.agh.config.ApplicationConfig;
import pl.edu.agh.domain.*;
import pl.edu.agh.services.interfaces.IEventsManagementService;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@ContextConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class EventManagementServiceTest {

    @Autowired
    private IEventsManagementService eventsManagementService;

    @Autowired
    private IUsersManagementService usersManagementService;

    private UserAccount userAccount;

    @Before
    public void beforeTest() {
        userAccount = usersManagementService.addNewUserAccount("JanKowalskiId", "Jan", "Kowalski", "jan@email.com", "JanKowalski", UserGroup.BOTH);
    }

    @Test
    public void getAllEventsTest() throws Exception {
        List<Event> events = eventsManagementService.getAllEvents();
        assertNotNull(events);
    }

    @Test
    public void getEventByIdTest() throws Exception {
        Event event = new Event(userAccount, "EventTitle", "EventDescription", new Date().toString(), "http://testUrl/1", new HashSet<String>(Arrays.asList("Tag1", "Tag2")), new ArrayList<Comment>());
        eventsManagementService.addNewEvent(event, userAccount);
        Event testEvent = eventsManagementService.getEventById(event.getId());
        assertNotNull(testEvent);
        assertEquals(event.getId(), testEvent.getId());
    }

    @Test
    public void addNewEventTest() throws Exception {
        List<Event> events = eventsManagementService.getAllEvents();
        assertNotNull(events);
        UserAccount userAccount = usersManagementService.addNewUserAccount("JanKowalskiId", "Jan", "Kowalski", "jan@email.com", "JanKowalski", UserGroup.BOTH);
        Event event = new Event(userAccount, "EventTitle", "EventDescription", new Date().toString(), "http://testUrl/1", new HashSet<String>(Arrays.asList("Tag1", "Tag2")), new ArrayList<Comment>());
        eventsManagementService.addNewEvent(event, userAccount);
        List<Event> events2 = eventsManagementService.getAllEvents();
        assertEquals(events.size() + 1, events2.size());
    }

    @Test
    public void addNewCommentTest() {
        Event event = new Event(userAccount, "EventTitle", "EventDescription", new Date().toString(), "http://testUrl/1", new HashSet<String>(Arrays.asList("Tag1", "Tag2")), new ArrayList<Comment>());
        eventsManagementService.addNewEvent(event, userAccount);
        Comment comment = new Comment(event, userAccount, Rating.GOOD, "My Simple Private Comment", true);
        eventsManagementService.addNewComment(event, comment, userAccount);
        Comment comment2 = new Comment(event, userAccount, Rating.OK, "My Simple Comment", false);
        eventsManagementService.addNewComment(event, comment2, userAccount);
        Event testEvent = eventsManagementService.getEventById(event.getId());
        assertNotNull(testEvent);
        assertNotNull(testEvent.getComments());
        assertEquals(testEvent.getComments().size(), 2);
    }

    @Test
    public void removeEventTest() throws Exception {
        Event event = new Event(userAccount, "EventTitle", "EventDescription", new Date().toString(), "http://testUrl/1", new HashSet<String>(Arrays.asList("Tag1", "Tag2")), new ArrayList<Comment>());
        eventsManagementService.addNewEvent(event, userAccount);
        List<Event> events = eventsManagementService.getAllEvents();
        eventsManagementService.removeEvent(event);
        List<Event> events2 = eventsManagementService.getAllEvents();
        assertEquals(events2.size(), events.size() - 1);

    }

    @Test
    public void getAllCreatorEventsTest() throws Exception {
        Event event = new Event(userAccount, "EventTitle", "EventDescription", new Date().toString(), "http://testUrl/1", new HashSet<String>(Arrays.asList("Tag1", "Tag2")), new ArrayList<Comment>());
        eventsManagementService.addNewEvent(event, userAccount);
        Event event2 = new Event(userAccount, "EventTitle", "EventDescription", new Date().toString(), "http://testUrl/1", new HashSet<String>(Arrays.asList("Tag1", "Tag2")), new ArrayList<Comment>());
        eventsManagementService.addNewEvent(event2, userAccount);
        List<Event> events = eventsManagementService.getAllCreatorEvents(userAccount);
        assertEquals(events.size(), 2);
    }

}
