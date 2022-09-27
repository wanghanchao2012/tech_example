package com.example.listener;

import com.example.event.WarnMessagePublishEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    @EventListener(classes = WarnMessagePublishEvent.class)
    public void processWarnMessageEvent(WarnMessagePublishEvent warnMessagePublishEvent) {
        System.out.println("message： " + warnMessagePublishEvent.getWarnMessage().getMessage());
        System.out.println("time： " + warnMessagePublishEvent.getWarnMessage().getTime());
    }
}
