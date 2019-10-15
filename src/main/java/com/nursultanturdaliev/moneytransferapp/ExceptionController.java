package com.nursultanturdaliev.moneytransferapp;


import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;


@Controller
@RequestMapping(path = "/api/exception")
public class ExceptionController {

    @ExceptionHandler({NoSuchElementException.class, JsonMappingException.class})
    public ModelAndView handleException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("custom-error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    @GetMapping("/")
    public ResponseEntity<String> fetchAll() {

        throw new NoSuchElementException("No such element");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> fetchOne(@PathVariable Long id) throws JsonMappingException {

        throw new JsonMappingException("Throwing Json Mapping Exception");
    }
}
