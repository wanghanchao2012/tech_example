package com.example.aop.service;

import com.example.entity.WarnMessageInfo;
import com.example.event.WarnMessagePublishEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 事件发布handle
 */
@Service
public class WarnMessagePublishImpl implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    public boolean register(WarnMessageInfo warnMessageInfo) {
        //消息发布
        applicationEventPublisher.publishEvent(new WarnMessagePublishEvent(this, warnMessageInfo));
        return true;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
