package com.example.spring.controller;

import com.example.spring.domain.entity.RspWrapper;
import com.example.spring.utils.MsgHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class I18nController {
    @Autowired
    MsgHelper msgHelper;

    @RequestMapping(value = "/change")
    @ResponseBody
    public RspWrapper changeLocal() {
        RspWrapper res = new RspWrapper<>();
        res.setData(msgHelper.get("name"));
        res.setCode(200);
        res.setMsg("ok");
        return res;
    }
}
