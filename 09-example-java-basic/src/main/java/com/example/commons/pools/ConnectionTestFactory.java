package com.example.commons.pools;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionTestFactory extends BaseKeyedPooledObjectFactory<String, ConnectionTest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTestFactory.class);

    @Override
    public ConnectionTest create(String key) throws Exception {
        return new ConnectionTest(key);
    }

    @Override
    public PooledObject<ConnectionTest> wrap(ConnectionTest value) {
        return new DefaultPooledObject<>(value);
    }

    public static void main(String[] args) {
        KeyedObjectPool<String, ConnectionTest> objectPool = new GenericKeyedObjectPool<>(new ConnectionTestFactory());
        try {
            //添加对象到池，重复的不会重复入池            
            objectPool.addObject("1");
            objectPool.addObject("2");
            objectPool.addObject("3");

            // 获得对应key的对象            
            ConnectionTest connectionTest1 = objectPool.borrowObject("1");
            LOGGER.info("borrowObject = {}", connectionTest1);

            // 释放对象            
            objectPool.returnObject("1", connectionTest1);

            //清除所有的对象            
            objectPool.clear();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}