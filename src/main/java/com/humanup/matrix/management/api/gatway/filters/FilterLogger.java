package com.humanup.matrix.management.api.gatway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class FilterLogger extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(FilterLogger.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("Request -> {} Call Source uri -> {}", request, request.getRequestURL());

        return null;
    }
}
