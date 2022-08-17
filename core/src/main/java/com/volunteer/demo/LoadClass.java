package com.volunteer.demo;

import com.mysql.jdbc.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.VM;

import java.io.InputStream;
import java.security.ProtectionDomain;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shengqiang
 * @date 2021-01-14 11:28*/


public class LoadClass extends ClassLoader{

/*    private final ClassLoader parent;

    private final ConcurrentHashMap<String, Object> parallelLockMap;*/

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.indexOf(".") + 1 + ".class";
        InputStream is = getClass().getResourceAsStream(fileName);
        return null;
    }



    private static class ParallelLoaders {
        private ParallelLoaders() {}

        // the set of parallel capable loader types
        private static final Set<Class<? extends ClassLoader>> loaderTypes =
                Collections.newSetFromMap(
                        new WeakHashMap<Class<? extends ClassLoader>, Boolean>());
        static {
            synchronized (loaderTypes) { loaderTypes.add(ClassLoader.class); }
        }


        static boolean register(Class<? extends ClassLoader> c) {
            synchronized (loaderTypes) {
                if (loaderTypes.contains(c.getSuperclass())) {
                    // register the class loader as parallel capable
                    // if and only if all of its super classes are.
                    // Note: given current classloading sequence, if
                    // the immediate super class is parallel capable,
                    // all the super classes higher up must be too.
                    loaderTypes.add(c);
                    return true;
                } else {
                    return false;
                }
            }
        }



        static boolean isRegistered(Class<? extends ClassLoader> c) {
            synchronized (loaderTypes) {
                return loaderTypes.contains(c);
            }
        }
    }


/*    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        //同步方法
        synchronized (getClassLoadingLock(name)) {
            //先查询该类是否被加载过
            Class<?> c = findLoadedClass(name);
            //未找到开始加载过程
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    //如果有父加载器，则先调用父加载器加载
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        //如果父加载器为空，则直接尝试用BootStrapClassLoader加载器去加载
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    //如果连启动类加载器都无法加载，则抛出ClassNotFound异常
                }

                if (c == null) {
                    long t1 = System.nanoTime();
                    //用自定义的类加载器加载
                    c = findClass(name);

                    //标记一下这是自定义的类加载器 记录一下统计数据
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                //链接指定的类。这个方法给Classloader用来链接一个类，如果这个类已经被链接过了，那么这个方法只做一个简单的返回。
                // 否则，这个类将被按照 Java™规范中的Execution描述进行链接。。。
                resolveClass(c);
            }
            return c;
        }
    }*/

    //如果加载的是不同类，锁对象是ClassLoader本身，如果加载的是同一个类，那么就从map中取出锁对象
/*    protected Object getClassLoadingLock(String className) {
        Object lock = this;
        //这里用到了parallelLockMap这个参数，用来标记是否需要并发注册
        if (parallelLockMap != null) {
            Object newLock = new Object();
            lock = parallelLockMap.putIfAbsent(className, newLock);
            if (lock == null) {
                lock = newLock;
            }
        }
        return lock;
    }*/

    private Class<?> findBootstrapClassOrNull(String name)
    {
        if (!checkName(name)) return null;

        return findBootstrapClass(name);
    }

    // true if the name is null or has the potential to be a valid binary name
    private boolean checkName(String name) {
        if ((name == null) || (name.length() == 0))
            return true;
        if ((name.indexOf('/') != -1)
                || (!VM.allowArraySyntax() && (name.charAt(0) == '[')))
            return false;
        return true;
    }

    private native Class<?> findBootstrapClass(String name);


}
