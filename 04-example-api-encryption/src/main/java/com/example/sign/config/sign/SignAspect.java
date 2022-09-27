package com.example.sign.config.sign;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import com.example.sign.config.exception.BusinessException;
import com.example.sign.util.Constants;
import com.example.sign.util.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class SignAspect {


    /**
     * pointcut.
     */
    @Pointcut("execution(@com.example.sign.config.sign.Signature * *(..))")
    private void verifySignPointCut() {
        // nothing
    }

    /**
     * verify sign.
     */
    @Before("verifySignPointCut()")
    public void verify() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String sign = request.getHeader(Constants.SIGN_HEADER);

        // must have sign in header
        if (CharSequenceUtil.isBlank(sign)) {
            throw new BusinessException("no signature in header: " + Constants.SIGN_HEADER);
        }

        // check signature
        try {
            long time = System.currentTimeMillis() - Constants.EXPIRE_TIME;
            // @RequestBody
            String bodyParam = null;
            String timeStamp = null;
            Map<String, String[]> requestParameterMap = null;
            if (request instanceof ContentCachingRequestWrapper) {
                bodyParam = new String(((ContentCachingRequestWrapper) request).getContentAsByteArray(), StandardCharsets.UTF_8);
            }
            // @RequestParam
            requestParameterMap = request.getParameterMap();
            Map<String, Object> map = JSONUtil.toBean(bodyParam, Map.class);
            if (map.containsKey(Constants.SIGN_TIME_STAMP)) {
                timeStamp = map.get(Constants.SIGN_TIME_STAMP).toString();
            }
            if (time > Long.parseLong(timeStamp)) {
                throw new BusinessException("invalid signature timeStamp expired");
            }
            String generatedSign = generatedSignature(bodyParam, requestParameterMap);
            if (!sign.equals(generatedSign)) {
                throw new BusinessException("invalid signature");
            }
        } catch (Throwable throwable) {
            if (StringUtils.isNotBlank(throwable.getMessage())) {
                throw new BusinessException(throwable.getMessage());
            } else {
                throw new BusinessException("invalid signature");
            }
        }
    }

    private String generatedSignature(String bodyParam, Map<String, String[]> requestParameterMap) throws IOException {
        Map<String, String[]> collect = requestParameterMap.entrySet().stream()
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        return SignUtil.sign(bodyParam, collect);
    }


}