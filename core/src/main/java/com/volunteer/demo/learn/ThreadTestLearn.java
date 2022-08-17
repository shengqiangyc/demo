/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.learn
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/26上午10:28
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.learn;

/**
 * 死锁
 * @author shengqiang
 * @date 2019/9/26上午10:28
 * sinceV1.0
 */
public class ThreadTestLearn implements Runnable{

    private static String A = "A";

    private static String B = "B";

    private int flag = 0;

    public ThreadTestLearn(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 0){
            synchronized (A){
                System.out.println("先获得A锁");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B){
                    System.out.println("再获得B锁");
                }
            }
        }
        if (flag == 1){
            synchronized (B){
                System.out.println("先获得B锁");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A){
                    System.out.println("后获得A锁");
                }
            }
        }
    }

    public static void main(String args[]){
        ThreadTestLearn learn1 = new ThreadTestLearn(0);
        ThreadTestLearn learn2 = new ThreadTestLearn(1);
        Thread thread1 = new Thread(learn1);
        Thread thread2 = new Thread(learn2);
        thread1.start();
        thread2.start();

    }
}
