package com.example.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MsgHelper implements MessageSourceAware {

    MessageSource messageSource;

    public void inti(MessageSource _messageSource) {
        this.messageSource = _messageSource;
    }

    /**
     * 获取单个国际化翻译值
     */
    public String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }

    @Override
    public void setMessageSource(MessageSource _messageSource) {
        messageSource = _messageSource;
    }
}