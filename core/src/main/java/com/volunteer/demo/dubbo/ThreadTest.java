/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.dubbo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/21下午7:57
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.dubbo;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/9/21下午7:57
 * sinceV1.0
 */
public class ThreadTest implements Runnable{

    private static String LOCKA = "lockA";

    private static String LOCKB = "lockB";

    int flag = 1;

    public ThreadTest(int flag){
        this.flag = flag;
    }

    @Override
    public void run() {

        if (flag == 1){
            synchronized (LOCKA){
                System.out.println("获得A锁,flag = " + flag);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCKB){
                    System.out.println("获得B锁,flag = "+ flag);

                }
            }
        }
        if (flag == 0){
            synchronized (LOCKB){
                System.out.println("获得B锁,flag = " + flag);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCKA){
                    System.out.println("获得A锁,flag = "+ flag);
                }
            }
        }

    }

    public static void main(String[] args){
        ThreadTest threadTest1 = new ThreadTest(1);
        ThreadTest threadTest2 = new ThreadTest(0);
        Thread thread1 = new Thread(threadTest1);
        Thread thread2 = new Thread(threadTest2);
        thread1.start();
        thread2.start();
    }
}
