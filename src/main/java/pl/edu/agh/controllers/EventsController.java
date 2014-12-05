package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.social.security.SocialUser;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.controllers.utils.SocialContollerUtils;
import pl.edu.agh.domain.Comment;
import pl.edu.agh.domain.Event;
import pl.edu.agh.domain.Rating;
import pl.edu.agh.domain.UserAccount;
import pl.edu.agh.services.interfaces.IEventsManagementService;
import pl.edu.agh.services.interfaces.ITwitterService;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by krzysztofczernek on 25/11/14.
 */
@Controller
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private IUsersManagementService usersManagementService;
    @Autowired
    private IEventsManagementService eventsManagementService;
    @Autowired
    private ITwitterService twitterService;

    @RequestMapping(method = RequestMethod.GET)
    public String listEvents(ModelMap model, HttpServletRequest request) {
        UserAccount currentUser = usersManagementService.getCurrentUser(request);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("events", eventsManagementService.getAllCreatorEvents(currentUser));
        model.addAttribute("event", new Event());
        return "events";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event, @RequestParam String hashtagsString, HttpServletRequest request) {
        HashSet<String> hashtags = new HashSet<String>(Arrays.asList(hashtagsString.split(" ")));
        event.setHashTags(hashtags);
        eventsManagementService.addNewEvent(event, usersManagementService.getCurrentUser(request));
        return "redirect:/events";
    }

    @RequestMapping(value = "delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventsManagementService.removeEvent(eventsManagementService.getEventById(eventId));
        return "redirect:/events";
    }

    @RequestMapping(value = "{eventId}", method = RequestMethod.GET)
    public String showEventDetails(ModelMap model, @PathVariable("eventId") Long eventId, HttpServletRequest request) {

        UserAccount currentUser = usersManagementService.getCurrentUser(request);
        Event event = eventsManagementService.getEventById(eventId);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("event", event);
        if (event.getCreator().getUserId().equals(currentUser.getUserId())) {
            model.addAttribute("comments", eventsManagementService.getEventComments(event));
        }
        model.addAttribute("comment", new Comment());
        model.addAttribute("ratings", Rating.values());

//        if (event.getHashTags().size() > 0) {
//            Twitter twitter = twitterService.getTwitterTemplate();
//            String searchQuery = event.getHashtagsString(" OR ");
//            SearchResults results = twitter.searchOperations().search(searchQuery, 10);
//            model.addAttribute("tweets", results.getTweets());
//        }

        return "eventDetails";
    }

    @RequestMapping(value = "{eventId}/comments", method = RequestMethod.POST)
    public String addEventComment(@PathVariable("eventId") Long eventId, @ModelAttribute("comment") Comment comment, HttpServletRequest request) {

        Event event = eventsManagementService.getEventById(eventId);
        UserAccount userAccount = usersManagementService.getCurrentUser(request);

        if (comment.isPrivateComment()) {
            eventsManagementService.addNewComment(event, comment, userAccount);
        } else {
            eventsManagementService.publishComment(event, comment, userAccount);
        }

        return "redirect:/events/" + event.getId();
    }
}