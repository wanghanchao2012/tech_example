package com.example.commons.pools;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.IOException;
import java.io.StringReader;

public class Run {
    public static void main(String[] args) throws IOException {
        ReaderUtil readerUtil = new ReaderUtil(new GenericObjectPool<StringBuffer>(new StringBufferFactory()));
        StringReader stringReader = new StringReader("xxxxxx");
        String s = readerUtil.readToString(stringReader);
        System.out.println(s);
    }
}
