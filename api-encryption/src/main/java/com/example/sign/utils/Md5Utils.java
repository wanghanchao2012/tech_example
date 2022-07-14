package com.example.sign.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * @author wanghanchao
 */
public class Md5Utils {

    /**
     * 生成签名
     *
     * @param parameters
     * @param key        商户ID
     * @return
     */
    public static String createSign(SortedMap<String, String> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 签名参数
     *
     * @param responseModel 响应数据签名返回给调用者
     * @return
     */
    /*public static SortedMap<String, String> createParameters(ResponseModel responseModel) {
        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
        if (responseModel != null) {
            sortedMap.put("timestamp", Convert.toStr(responseModel.getTimestamp()));
            JSONObject json = JSONUtil.parseObj(responseModel, false);
            if (responseModel.getData() != null) {
                sortedMap.put("data", json.get("data").toString());
            }
        }
        return sortedMap;

    }*/

}