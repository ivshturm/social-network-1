package ru.sberbank.project.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView("exception/exception");
        modelAndView.addObject("exception", e);
        return modelAndView;

    }
}
