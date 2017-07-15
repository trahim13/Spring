package org.trahim.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import org.trahim.objects.User;

import java.util.Locale;

@Controller
public class LoginController {

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(@ModelAttribute User user, HttpSession session, Locale locale) {
		System.out.println(locale.getDisplayLanguage());
		System.out.println(messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));
		user.setName("Name");
		return new ModelAndView("login", "user", user);
	}



	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(Locale locale, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, ModelMap modelMap) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("locale", messageSource.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("main");
		}

		return modelAndView;
	}


	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView faild() {
		return new ModelAndView("login-failed", "message", "Login faild!");

	}

	@RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/xml")
//	@RequestMapping(value = "/get-json-user", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
//	public User getJsonUser(@RequestParam("name") String name) {
		User user = new User();
		user.setName(name);
		user.setAdmin(admin);
		return user;
	}

	@RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> setJsonUser(@RequestBody User user) {
		logger.info(user.getName());
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
}


