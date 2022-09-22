package com.example.threads;


public class YieldDemo extends Thread {

    public YieldDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldDemo yt1 = new YieldDemo("张三");
        YieldDemo yt2 = new YieldDemo("李四");
        yt1.start();
        yt2.start();
    }
}