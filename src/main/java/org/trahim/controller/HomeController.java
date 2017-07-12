package org.trahim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 12.07.2017.
 */
@Controller
public class HomeController {
    @RequestMapping
    public String home(ModelMap model) {

        return "home";

    }
}
