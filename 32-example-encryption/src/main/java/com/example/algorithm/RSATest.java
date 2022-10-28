package com.example.algorithm;

import com.example.algorithm.utils.*;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

public class RSATest {

    public static void main(String[] args) throws Exception {

        // 使用公钥、私钥对象加解密
        List<Key> keyList = RSAUtils.getRSAKeyObject(1024);
        RSAPublicKey puk = (RSAPublicKey) keyList.get(0);
        RSAPrivateKey prk = (RSAPrivateKey) keyList.get(1);
        String message = "abcdefg";
        String encryptedMsg = RSAUtils.encryptByPublicKey(message, puk);
        String decryptedMsg = RSAUtils.decryptByPrivateKey(encryptedMsg, prk);
        System.out.println("encryptedMsg==>" + encryptedMsg);
        System.out.println("decryptedMsg==>" + decryptedMsg);
        System.out.println("---------------------------------------------------");
        System.out.println("object key ! message ==  decryptedMsg ? " + message.equals(decryptedMsg));

        // 使用字符串生成公钥、私钥完成加解密
        List<String> keyStringList = RSAUtils.getRSAKeyString(1024);
        String pukString = keyStringList.get(0);
        String prkString = keyStringList.get(1);
        System.out.println("公钥:" + pukString);
        System.out.println("私钥:" + prkString);
        // 生成公钥、私钥
        puk = RSAUtils.getPublicKey(pukString);
        prk = RSAUtils.getPrivateKey(prkString);
        encryptedMsg = RSAUtils.encryptByPublicKey(message, puk);
        decryptedMsg = RSAUtils.decryptByPrivateKey(encryptedMsg, prk);
        System.out.println("string key ! message ==  decryptedMsg ? " + message.equals(decryptedMsg));

    }
}