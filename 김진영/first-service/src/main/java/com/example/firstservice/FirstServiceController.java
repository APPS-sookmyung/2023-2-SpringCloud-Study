package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// http://localhost:8081/welcome
// http://localhost:8081/first-service/welcome
@RestController
@RequestMapping("/first-service/")
@Slf4j
public class FirstServiceController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){ //header값을 받아와서 실제 header 변수에 저장
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/3rd-homework")
    public String homework(HttpServletRequest request) {
        String header= request.getHeader("first-request");
        return header;
    }

}
