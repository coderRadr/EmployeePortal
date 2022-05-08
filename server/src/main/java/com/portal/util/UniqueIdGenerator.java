package com.portal.util;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * Filter bean used to add unique-id txt for Request
 */
@Component
public class UniqueIdGenerator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String randomId = UUID.randomUUID().toString();
        MDC.put(ConstantUtil.REQUEST_ID, randomId);
        chain.doFilter(request, response);
        MDC.clear();
    }
}
