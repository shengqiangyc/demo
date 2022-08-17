/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.Singleton
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2020-03-1011:23
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.Singleton;

/**
 *
 * @author shengqiang
 * @date 2020-03-1011:23
 * sinceV1.0
 */
public class LazyThree {

    private static LazyThree getInstance(){
        return LazyHolder.lazyThree;
    }

    private static class LazyHolder{
        private static LazyThree lazyThree = new LazyThree();
    }
}
