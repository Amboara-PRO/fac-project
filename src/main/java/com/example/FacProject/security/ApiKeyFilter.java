package com.example.FacProject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class ApiKeyFilter implements Filter {

    private static final String API_KEY = "agri-secure-key";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(
            jakarta.servlet.ServletRequest request,
            jakarta.servlet.ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String apiKey = httpRequest.getHeader("x-api-key");

        if (apiKey == null || !apiKey.equals(API_KEY)) {

            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            httpResponse.setContentType("text/plain");

            httpResponse.getWriter().write("Bad credentials");

            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}