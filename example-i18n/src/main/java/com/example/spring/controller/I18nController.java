package com.example.spring.controller;

import com.example.spring.domain.entity.RspWrapper;
import com.example.spring.utils.MsgHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class I18nController {
    @Autowired
    MsgHelper msgHelper;

    @RequestMapping(value = "/change")
    @ResponseBody
    public RspWrapper changeLocal(String l) {
        System.out.println(LocaleContextHolder.getLocale().getLanguage());
        RspWrapper res = new RspWrapper<>();
        System.out.println(msgHelper.get("500"));
        res.setData(msgHelper.get("500"));
        return res;
    }
}
