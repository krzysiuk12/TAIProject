package pl.edu.agh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.agh.services.interfaces.ITwitterService;
import pl.edu.agh.services.interfaces.IUsersManagementService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    private IUsersManagementService usersManagementService;

    @Autowired
    public MainController(IUsersManagementService usersManagementService, ITwitterService twitterService) {
        this.usersManagementService = usersManagementService;
    }

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request, Principal currentUser, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "Successfully signed in!");
		modelAndView.addObject("currentUser", usersManagementService.getCurrentUser(request));
		modelAndView.setViewName("hello");
		return modelAndView;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, Principal currentUser, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		if (error != null) {
			modelAndView.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			modelAndView.addObject("msg", "Successfully signed out!");
		}
		modelAndView.setViewName("login");

		return modelAndView;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

}