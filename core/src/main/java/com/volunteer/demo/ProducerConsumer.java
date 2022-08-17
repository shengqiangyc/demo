/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/27下午5:43
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/9/27下午5:43
 * sinceV1.0
 */
public class ProducerConsumer {


    public static final String Lock = "Lock";
    private int max = 10;
    private int count = 0;

    class Producer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < max ; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Lock){
                    while (count == max){
                        try {
                            Lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count ++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产,目前共有:"+count);
                    Lock.notifyAll();
                }

            }
        }
    }

    class consumer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < max ; i++) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Lock){
                    while (count == 0){
                        try {
                            Lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count --;
                    System.out.println(Thread.currentThread().getName() + "消费者消费,目前共有:"+count);
                    Lock.notifyAll();
                }
            }
        }
    }

    public static void main(String args[]){
        ProducerConsumer producerConsumer = new ProducerConsumer();
        new Thread(producerConsumer.new Producer()).start();
        new Thread(producerConsumer.new consumer()).start();
        new Thread(producerConsumer.new Producer()).start();
        new Thread(producerConsumer.new Producer()).start();
        new Thread(producerConsumer.new consumer()).start();
        new Thread(producerConsumer.new Producer()).start();
        new Thread(producerConsumer.new consumer()).start();
        new Thread(producerConsumer.new Producer()).start();

    }




}
