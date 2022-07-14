package com.example.aspect;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.sign.except.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.example.sign.utils.Md5Utils.createSign;

@Aspect
@Component
public class SignatureAspect {

    /**
     * 秘钥,也可为每个对接的用户单独设置
     */
    private static final String SECRET = "test";

    /**
     * 验签切点(完整的找到设置的文件地址)
     */
    @Pointcut("execution(@com.example.sign.annotation.SignAuth * *(..))")
    private void verifyUserKey() {
    }

    /**
     * 获取请求数据，并校验签名数据
     */
    @Before("verifyUserKey()")
    public void doBasicProfiling(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String sign = "";
        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
        for (Object obj : point.getArgs()) {
            JSONObject jsonObject = JSONUtil.parseObj(obj);
            if (!StrUtil.isEmptyIfStr(jsonObject.get("sign"))) {
                sign = jsonObject.get("sign").toString();
            }
            for (Map.Entry<String, Object> requestMap : jsonObject.entrySet()) {
                String key = requestMap.getKey();
                Object value = requestMap.getValue();
                sortedMap.put(key, Convert.toStr(value));
            }
        }
        //新的签名
        String newSign = createSign(sortedMap, SECRET);
        if (!newSign.equals(sign.toUpperCase())) {
            throw new ServiceException(2001, "验签失败，请检查sign加签是否正确!");
        }
    }

    /**
     * @param point
     * @param responseObject 返回参数
     */
    /*@AfterReturning(pointcut = "verifyUserKey()", returning = "responseObject")
    public void afterReturning(JoinPoint point, Object responseObject) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (responseObject instanceof ResponseModel) {
            ResponseModel responseModel = (ResponseModel) responseObject;
            responseModel.setTimestamp(S\ystem.currentTimeMillis());
            String sign = Md5Utils.createSign(Md5Utils.createParameters(responseModel), SECRET);
            responseModel.setSign(sign);
        }
    }*/
}