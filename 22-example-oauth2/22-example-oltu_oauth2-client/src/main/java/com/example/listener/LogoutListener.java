package com.example.listener;

import com.example.session.LocalSessionMappingStorage;
import com.example.session.SessionMappingStorage;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 单点登出Listener
 * <p>
 * 注：用于本地session过期，删除accessToken和session的映射关系
 *
 * @author Joe
 */
public final class LogoutListener implements HttpSessionListener {

    private static SessionMappingStorage sessionMappingStorage = new LocalSessionMappingStorage();

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        final HttpSession session = event.getSession();
        sessionMappingStorage.removeBySessionById(session.getId());
    }

    public void setSessionMappingStorage(SessionMappingStorage sms) {
        sessionMappingStorage = sms;
    }

    public static SessionMappingStorage getSessionMappingStorage() {
        return sessionMappingStorage;
    }
}
