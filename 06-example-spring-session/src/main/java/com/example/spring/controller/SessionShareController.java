package com.example.spring.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * SessionShareController <br>
 * 〈session共享控制器〉
 */
@RestController
@RequestMapping(value = "/session")
public class SessionShareController {

    @Value("${server.port}")
    Integer port;


    @GetMapping(value = "/set")
    public String set(HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("user", "123456");
        System.out.println("你好，这是我的分支代码!");
        System.out.println("成于西周\n" +
                "顾颉刚关于《周易》卦爻辞制作年代的考证结论，认为周初作。李学勤也认为顾颉刚此文“推定经文卦爻辞‘著作年代当在西周初叶’”，“为学者所遵信，可以说基本确定了《周易》卦爻辞年代的范围，是极有贡献的”。 [6] \n" +
                "成于战国\n" +
                "大多学者认为《易传》成书于战国。易传的成书问题，自欧阳修《易童子问》与苏轼之后，数百年来已经罕有人再信是孔子所做了，钱穆、顾颉刚、冯友兰、郭沫若、李镜池等等海内外的学者名家均断定司马迁《史记》的说法不足信。其称易传为孔子所做，若非司马迁之误，就必是汉儒刘歆所伪窜。根据《易传》的内容来看，应是在孟子、荀子的性命天道之学出现以后的作品，有明显的黄老道家与阴阳家色彩。 [7] \n" +
                "成于西汉\n" +
                "顾颉刚根据箕子和康侯两条卦爻辞，指出《周易》卦爻辞为文王所作的传统说法不可信 [8]  。顾颉刚继《周易卦爻辞中的故事》之后作《论易系辞传中观象制器的故事》，是对其所说《周易》卦爻辞所无的观象制器故事的专门考论，意在证明《系辞传》观象制器章讲到古史帝系人物的话是西汉后期人的说法。 [9] \n" +
                "关于《系辞传》与《世本》的关系，胡适指出，“《世本》所据传说，必有一部分是很古的，但《世本》是很晚的书，《系辞》不会在其后” [10]  ，“《世本》不采《系辞》，也许是因为《系辞》所说制作器物太略了，不够过瘾。《系辞》那一章所说，只重在制器尚象，并不重在假造古帝王之名。若其时已有苍颉沮诵作书契之传说，又何必不引用而仅泛称‘后世圣人’呢？ [11]  ”\n");
        return String.valueOf(port);
    }

    @GetMapping(value = "get")
    public String get(HttpSession session) {
        return "用户:" + session.getAttribute("user") + ",端口:" + port;
    }
}
