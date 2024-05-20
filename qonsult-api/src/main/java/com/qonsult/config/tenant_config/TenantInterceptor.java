package com.qonsult.config.tenant_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alon Segal on 23/03/2017.
 */

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

//    @Value("${jwt.header}")
//    private String tokenHeader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String tenant = request.getHeader("Tenant");
        TenantContext.setCurrentTenant(tenant);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        TenantContext.clear();
    }
}