package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j //lombok 지원 - 이 어노테이션을 붙이면 logger 객체를 굳이 안만들더라도 log사용 가능
@Component
public class ZuulLogginFilter extends ZuulFilter {

    //화면에 어떤 요청이 들어왔는지 로그를 표현
    //Logger logger = LoggerFactory.getLogger(ZuulLogginFilter.class);
    @Override
    public Object run() throws ZuulException {
        log.info("********************* printing logs: ");

        //요청 정보를 출력
        //HttpServletRequest request=null;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request= ctx.getRequest();

        log.info("********************* "+ request.getRequestURI()); //사용자가 어떤 요청 정보 uri를 요청했는지 알 수 있음

        return null;
    }
    @Override
    public String filterType() {
        return "pre"; //사전 필터: pre
    }

    @Override
    public int filterOrder() {
        return 1; //filter는 하나밖에 없으므로 1번으로 설정 
    }

    @Override
    public boolean shouldFilter() {
        return true; //filter을 쓰겠다고 지정
    }


}
