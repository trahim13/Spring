package org.trahim.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.trahim.objects.User;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.Locale;

@Controller
@SessionAttributes(value = "user")
public class LoginController {

	public static final int WEAK_STRENGTH = 1;
	public static final int FAIR_STRENGTH = 5;
	public static final int STRONG_STRENGTH = 7;

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@ModelAttribute
	public User createNewUser() {
		return new User("usernamevalue");
	}

	@RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	public @ResponseBody
	String checkStrenght(@RequestParam String password) {
		if (password.length() >= WEAK_STRENGTH & password.length() < FAIR_STRENGTH) {
			return "СЛАБЫЙ";
		} else if (password.length() >= FAIR_STRENGTH & password.length() < STRONG_STRENGTH) {
			return "Средний";
		} else if (password.length() >= STRONG_STRENGTH) {
			return "Сильный";
		}
		return "";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(@ModelAttribute User user, HttpSession session, Locale locale) {

		logger.info(locale.getDisplayLanguage());
		logger.info(messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));
		return "login";
	}

	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(Locale locale, @Valid @ModelAttribute( "user") User user, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		if (!bindingResult.hasErrors()) {
			RedirectView redirectView = new RedirectView("mainpage");
			redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			modelAndView.setView(redirectView);
			redirectAttributes.addFlashAttribute("locale", messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));
		} else {
			modelAndView.setViewName("main");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String goMainPage(HttpServletRequest request) {

//		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
//		if (map != null) {
//			logger.info("redirect!");
//		} else {
//			logger.info("update!");
//		}

		return "main";
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView failed() {
		return new ModelAndView("login-failed", "message", "Login failed!");
	}

	@RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
		User user = new User();
		user.setName(name);
		user.setAdmin(admin);
		return user;
	}

	@RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> setJsonUser(@RequestBody User user) {
		logger.info(user.getName());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
