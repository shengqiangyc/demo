/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/11/15下午3:00
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

import java.util.concurrent.Semaphore;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/11/15下午3:00
 * sinceV1.0
 */
public class ProducerConsumer2 {

    private static Integer count = 0;
    //创建三个信号量
    final Semaphore notFull = new Semaphore(10);
    final Semaphore notEmpty = new Semaphore(0);
    final Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {
        ProducerConsumer2 producerConsumer2 = new ProducerConsumer2();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
    }
    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    notFull.acquire();
                    mutex.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    notEmpty.release();
                }
            }
        }
    }
    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    notEmpty.acquire();
                    mutex.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    notFull.release();
                }
            }
        }
    }

}
