package com.volunteer.demo;

/**
 * @author shengqiang
 * @date 2020-08-11 14:13
 */
public class ThreadLocalTest implements Runnable{

    private ThreadLocal<String> local1 = new ThreadLocal<>();
    private ThreadLocal<String> local2 = new ThreadLocal<>();

    private String str1 = "111";
    private String str2 = "222";

    @Override
    public void run() {
        local1.set("111" + Thread.currentThread());
        local2.set("222" + Thread.currentThread());
        str1 += Thread.currentThread();
        str2 += Thread.currentThread();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(local1.get());
        System.out.println(local2.get());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(str1);
        System.out.println(str2);
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        Thread thread1 = new Thread(test);
        Thread thread2 = new Thread(test);
        thread1.start();
        thread2.start();
    }
}
