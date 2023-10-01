package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class ZuulLoggingFilter extends ZuulFilter {

    @Override
    public Object run() throws ZuulException {
        log.info("*************** printing logs: ");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        log.info("*************** " + request.getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 필터 사용 여부
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

}
