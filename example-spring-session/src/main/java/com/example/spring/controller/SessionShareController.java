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
