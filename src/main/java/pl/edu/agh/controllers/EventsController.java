package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.domain.*;
import pl.edu.agh.services.implementations.EventsManagementService;
import pl.edu.agh.services.interfaces.IEventsManagementService;
import pl.edu.agh.services.interfaces.ITwitterService;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by krzysztofczernek on 25/11/14.
 */
@Controller
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private IEventsManagementService eventsManagementService;
    @Autowired
    private IUsersManagementService usersManagementService;
    @Autowired
    private ITwitterService twitterService;

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public String listEvents(ModelMap model) {
        model.addAttribute("events", eventsManagementService.getAllEvents());
        model.addAttribute("event", new Event());
        return "events";
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String addEvent(@ModelAttribute("event") Event event, @RequestParam String hashtagsString) {
        HashSet<String> hashtags = new HashSet<String>(Arrays.asList(hashtagsString.split(" ")));
        event.setHashTags(hashtags);

        eventsManagementService.addNewEvent(event);
        return "redirect:/events";
    }

    @RequestMapping(value = "delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {

        eventsManagementService.removeEvent(eventsManagementService.getEventById(eventId));
        return "redirect:/events";
    }

    @RequestMapping(value = "{eventId}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String showEventDetails(ModelMap model, @PathVariable("eventId") Long eventId) {

        Event event = eventsManagementService.getEventById(eventId);
        model.addAttribute("comments", eventsManagementService.getEventComments(event));
        model.addAttribute("comment", new Comment());
        model.addAttribute("event", event);

        Twitter twitter = twitterService.getTwitterTemplate();
        String searchQuery = "#hashtag"; // TODO
        SearchResults results = twitter.searchOperations().search(searchQuery);
        model.addAttribute("tweets", results.getTweets());

        return "eventDetails";
    }

    @RequestMapping(value = "{eventId}/comments", method = RequestMethod.POST)
    @Transactional
    public String addEventComment(@PathVariable("eventId") Long eventId, @ModelAttribute("comment") Comment comment) {

        Event event = eventsManagementService.getEventById(eventId);
        comment.setCommenter(usersManagementService.addNewUser("user", "password", UserGroup.CREATOR));
        comment.setRating(Rating.OK);
        comment.setPrivateComment(true);

        eventsManagementService.addNewComment(event, comment);

        return "redirect:/events/" + event.getId();
    }
}