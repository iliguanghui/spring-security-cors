package com.lgypro.listener;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;

@Component
public class FullServletEventListener implements HttpSessionListener, HttpSessionIdListener, HttpSessionAttributeListener,
    ServletRequestListener, ServletRequestAttributeListener,
    ServletContextListener, ServletContextAttributeListener {
    // session related
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        String sessionId = event.getSession().getId();
        System.out.printf("🌽Created session '%s'%n", sessionId);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String sessionId = event.getSession().getId();
        System.out.printf("🌽Destroyed session '%s'%n", sessionId);
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        String newSessionId = event.getSession().getId();
        System.out.printf("🌽Changed session id, from '%s' to '%s'%n", oldSessionId, newSessionId);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.printf("🌽Added attribute '%s' to session '%s', value is %s%n", event.getName(), event.getSession().getId(), event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.printf("🌽Removed attribute '%s' from session '%s', old value is %s%n", event.getName(), event.getSession().getId(), event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.printf("🌽Replaced attribute '%s' from session '%s', new value is %s%n", event.getName(), event.getSession().getId(), event.getValue());
    }
    // request related

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        System.out.printf("🫦Initialized request %s%n", requestLine((HttpServletRequest) event.getServletRequest()));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.printf("🫦Destroyed request %s%n", requestLine((HttpServletRequest) event.getServletRequest()));
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.printf("🫦Added attribute '%s' to request '%s', value is %s%n", event.getName(), requestLine((HttpServletRequest) event.getServletRequest()), event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        System.out.printf("🫦Removed attribute '%s' to request '%s', value is %s%n", event.getName(), requestLine((HttpServletRequest) event.getServletRequest()), event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        System.out.printf("🫦Replaced attribute '%s' to request '%s', value is %s%n", event.getName(), requestLine((HttpServletRequest) event.getServletRequest()), event.getValue());
    }

    // context related

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.printf("🦊Initialized servlet context %s%n", event.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.printf("🦊Destroyed servlet context %s%n", event.getServletContext());
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.printf("🦊Added attribute '%s' to servlet context '%s', value is %s%n", event.getName(), event.getServletContext(), event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.printf("🦊Removed attribute '%s' to servlet context '%s', value is %s%n", event.getName(), event.getServletContext(), event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.printf("🦊Replaced attribute '%s' to servlet context '%s', value is %s%n", event.getName(), event.getServletContext(), event.getValue());
    }

    private static String requestLine(HttpServletRequest request) {
        return request.getMethod() + " " + UrlUtils.buildRequestUrl(request);
    }
}
