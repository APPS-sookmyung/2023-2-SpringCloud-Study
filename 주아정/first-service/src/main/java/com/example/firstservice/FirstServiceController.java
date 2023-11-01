package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service/") // URI 지정 (루트)
@Slf4j
public class FirstServiceController {
    Environment env;

    public FirstServiceController(Environment env) {
        this.env = env;
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/3rd-homework")
    public String homework() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("first-request");
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return String.format("Hi, there. This a message from First Service on PORT %s", env.getProperty("local.server.port"));
    }
}

