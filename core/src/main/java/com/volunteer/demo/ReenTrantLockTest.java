/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2019/9/29下午5:45
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ${TODO}
 * @author shengqiang
 * @date 2019/9/29下午5:45
 * sinceV1.0
 */
public class ReenTrantLockTest {

    private AtomicReference<Thread> cas = new AtomicReference<>();
    private int count = 0;

    private void lock(){
        Thread current = Thread.currentThread();
        if (current == cas.get()){
            count ++;
            return;
        }
        while (!cas.compareAndSet(null,current)){
            System.out.println("自旋等待锁");

        }
    }

    private void unlock(){
        Thread current = Thread.currentThread();
        if (current == cas.get()){
            if (count > 0) {
                count--;
            } else {
                cas.compareAndSet(current,null);
            }
        }

    }

}
