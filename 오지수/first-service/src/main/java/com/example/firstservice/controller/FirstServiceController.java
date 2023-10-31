package com.example.firstservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from First Service.";
    }

    @GetMapping("/3rd-homework")
    public String homework() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        return httpServletRequest.getHeader("first-request");
    }
}
