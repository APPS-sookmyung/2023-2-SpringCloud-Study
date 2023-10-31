package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

// http://localhost:8081/welcome
// http://localhost:8081/first-service/welcome
@RestController
@RequestMapping("/first-service/")
@Slf4j
public class FirstServiceController {
    Environment env;

    @Autowired
    public FirstServiceController(Environment env){
        this.env=env;
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){ //header값을 받아와서 실제 header 변수에 저장
        log.info(header);
        return "Hello World in First Service.";
    }

//    @GetMapping("/check")
//    public String check(){
//        return "Hi there. This is a message from First Service.";
//    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());

        return String.format("Hi there. This is a message from First Service on PORT %s.",
                env.getProperty("local.server.port")); //환경변수에 등록된 정보에서 실제로 할당된 정보를 가져오는 것
    }

    @GetMapping("/3rd-homework")
    public String homework(HttpServletRequest request) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        return httpServletRequest.getHeader("first-request");
    }

}
