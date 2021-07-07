package com.cadfuncionario.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFIlter implements Filter {

    @Autowired(required=true)
    private CadfuncionarioProperty cadfuncionarioProperty;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Access-Control-Allow-Origin", this.cadfuncionarioProperty.getOrigemPermitida());
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(req.getMethod()) && this.cadfuncionarioProperty.getOrigemPermitida().equals(req.getHeader("Origin"))) {
            resp.setHeader("Access-Control-Allow-Methods", "OPTIONS, POST, GET, PUT, DELETE");
            resp.setHeader("Access-Control-Allow-Headers", "Authorization, Content-type, Accept");
            resp.setHeader("Access-Control-Max-Age", "3600");
        } else {
            chain.doFilter(request, response);
        }

    }

}