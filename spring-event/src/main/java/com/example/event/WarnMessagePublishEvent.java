package com.example.event;

import com.example.entity.WarnMessageInfo;
import org.springframework.context.ApplicationEvent;

/**
 * 定义一个事件
 */
public class WarnMessagePublishEvent extends ApplicationEvent {
    private static final long serialVersionUID = -5481658020206295565L;
    private WarnMessageInfo warnMessageInfo;

    public WarnMessagePublishEvent(Object source, WarnMessageInfo message) {
        super(source);
        this.warnMessageInfo = message;
    }

    public WarnMessageInfo getWarnMessage() {
        return warnMessageInfo;
    }
}
