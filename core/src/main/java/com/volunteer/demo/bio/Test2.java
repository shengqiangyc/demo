package com.volunteer.demo.bio;

/**
 * @author shengqiang
 * @date 2021-01-20 20:35
 */
public class Test2 {

    public static void main(String[] args) {
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
    }

    private static class Test {

    }
}
