package com.nursultanturdaliev.moneytransferapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    String home() {
        return homeService.welcome();
    }
}
