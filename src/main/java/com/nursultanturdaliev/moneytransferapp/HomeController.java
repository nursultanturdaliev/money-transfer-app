package com.nursultanturdaliev.moneytransferapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    String home() {
        logger.info("[Home] Request received");
        return homeService.welcome();
    }
}
