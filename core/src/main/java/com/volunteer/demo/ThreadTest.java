/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/11/19下午5:32
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/11/19下午5:32
 * sinceV1.0
 */
public class ThreadTest extends Thread{

    public ThreadTest() {
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread.getName()=" + Thread.currentThread().getName());
        System.out.println("this.name()=" + this.getName());
        System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
        System.out.println("CountOperate---end");
    }

    @Override
    public void run() {
        System.out.println("run begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("this.getName=" + this.getName());
        System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
        System.out.println("run---end");
    }

    public static void main(String[] args){
        ThreadTest countOperate = new ThreadTest();
        Thread thread = new Thread(countOperate);
        thread.setName("A");
        thread.start();
    }
}
