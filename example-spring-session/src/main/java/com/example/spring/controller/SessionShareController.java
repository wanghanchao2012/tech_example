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
        System.out.println("《同仁堂》贯口完整版台词：\n" +
                "\n" +
                "表演者：于谦\n" +
                "\n" +
                "同仁堂，开的本是老药铺。\n" +
                "\n" +
                "先生好比这个甩手自在王。\n" +
                "\n" +
                "药王爷就在上边坐，十大名医列在两旁。\n" +
                "\n" +
                "先拜药王后拜你，那么你是药王爷的大徒弟。\n" +
                "\n" +
                "这个药王爷，本姓孙，提龙跨虎，手捻着针。\n" +
                "\n" +
                "内科先生孙思邈，外科的先生华佗高。\n" +
                "\n" +
                "孙思邈，医术高，三十二岁入的堂朝。\n" +
                "\n" +
                "正宫的国母得了病，走线号脉治好了。\n" +
                "\n" +
                "一针治好娘娘的病，两针治好了龙一条。\n" +
                "\n" +
                "万岁一见龙心喜，钦身点他在当朝。\n" +
                "\n" +
                "封他文官他也不要，封他武将就把头摇。\n" +
                "\n" +
                "万般出了无计奈，钦身赐柬大黄袍。\n" +
                "\n" +
                "在一旁怒恼哪一个，惹恼了敬德老英豪。\n" +
                "\n" +
                "为臣我东挡西杀南征北战跨马抡鞭功劳大，\n" +
                "\n" +
                "你为何不赐那黄袍？\n" +
                "\n" +
                "一副钢鞭拿在了手，手拿钢鞭赶黄袍，一赶赶到八里桥。\n" +
                "\n" +
                "药王爷，妙法高，脱去了黄袍换红袍。\n" +
                "\n" +
                "黄袍供在药王阁，黎民百姓才把香烧。\n" +
                "\n" +
                "王阁里面有栏柜，那栏柜三尺三寸三分三厘高。\n" +
                "\n" +
                "一边撂着轧药碾，一边供着铡药刀。\n" +
                "\n" +
                "铡药刀，亮堂堂，几味草药您老先尝。\n" +
                "\n" +
                "先铡这个牛黄与狗宝，后铡槟榔与麝香。\n" +
                "\n" +
                "桃仁陪着杏仁睡，二人躺在了沉香床。\n" +
                "\n" +
                "睡到三更茭白叶，胆大的木贼跳进墙。\n" +
                "\n" +
                "盗走了水银五十两，金毛的狗儿叫汪汪。\n" +
                "\n" +
                "有丁香去送信，人参这才坐大堂。\n" +
                "\n" +
                "佛手抄起甘草棍，棍棍打在了陈皮上。\n" +
                "\n" +
                "打得这个陈皮流鲜血，鲜血甩在了木瓜上。\n" +
                "\n" +
                "大风丸，小风丸，胖大海，滴溜圆，\n" +
                "\n" +
                "狗皮膏药贴伤寒。\n" +
                "\n" +
                "我有心接着药名往下唱，\n" +
                "\n" +
                "唱到明儿个也唱不完，\n" +
                "\n" +
                "我唱的是祝各位身体健康福寿双全！");
        return String.valueOf(port);
    }

    @GetMapping(value = "get")
    public String get(HttpSession session) {
        return "用户:" + session.getAttribute("user") + ",端口:" + port;
    }
}
