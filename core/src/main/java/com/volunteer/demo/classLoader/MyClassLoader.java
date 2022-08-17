package com.volunteer.demo.classLoader;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.DateFormatter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shengqiang
 * @date 2021-01-21 10:56
 */
@Slf4j
public class MyClassLoader extends ClassLoader{

    //执行加载的class文件的路径
    private String path;

    MyClassLoader(String path){
        this.path = path;
    }

    MyClassLoader(ClassLoader parent,String path){
        super(parent);
        this.path = path;
    }
/*
    static {
        registerAsParallelCapable();
    }
*/

    //用于寻找类文件
    @Override
    protected Class findClass(String name){
        log.info("自定义类加载器开始加载,name = {}",name);
        byte[] b = loadClassData(name);
        return defineClass(name,b,0,b.length);
    }

    //从路径读取class文件，并转化为二进制文件
    private byte[] loadClassData(String name) {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try{
            in  = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i=0;
            while ((i = in.read()) != -1){
                out.write(i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent(),"/Users/shengqiang/Desktop/");
        Class<?> c = myClassLoader.loadClass("ClassLoaderTestDemo");
        System.out.println(c.getCanonicalName());
        System.out.println(c.getClassLoader());
        String a = "abcGts";
        List<Integer> array = Lists.newArrayList(1,2,3);
        List<Long> intArray = array.stream().map(x -> x +1L).collect(Collectors.toList());
        //List<Long> aaa = array.stream().reduce((x,y) -> x + 1);

        Lists.newArrayList("abc","ABc","ayYYcc").stream();

        //System.out.println(ClassLoaderTestDemo.class.getCanonicalName());
        c.newInstance();
    }
}
