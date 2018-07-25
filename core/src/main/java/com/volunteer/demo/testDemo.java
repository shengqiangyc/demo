/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/6/11下午3:07
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

import com.google.gson.Gson;
import com.volunteer.demo.form.CountForm;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/6/11下午3:07
 * sinceV1.0
 */
public class testDemo {

    /*public static void main(String[] args) throws Exception{
        *//*XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("jintian");
        XSSFRow row = sheet.createRow(0);
        int colum = 0;
        XSSFCell cell = row.createCell(colum);
        cell.setCellValue("你好");
        colum++;
        cell = row.createCell(colum);
        cell.setCellValue("你好1");
        FileOutputStream stream = new FileOutputStream("/Users/shengqiang/Documents/测试/test2.xlsx");
        workbook.write(stream);
        stream.close();*//*
        *//*File file = new File("/Users/shengqiang/Documents/测试/test.txt");
        FileInputStream stream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        for(int i = 0;i < bytes.length;i++){
            bytes[i] = (byte)stream.read();
        }
        stream.close();
        System.out.println(new String(bytes));*//*
        *//*List<String> testList = new ArrayList<>();
        testList.add("111");
        Object a[] = testList.toArray();
        System.out.println(a[0]);
        String aa = "Admin";
        System.out.println(aa.toLowerCase());
        CountForm form = new CountForm();
        form.setCount(11);
        String a1[] = {"1","2"};
        Integer b = Integer.parseInt("1024");
        Integer c = Integer.valueOf("1024").intValue();
        Gson gson = new Gson();*//*
        String a = "tao";
        String b = "bao";
        String c = "taobao";
        System.out.println((a+b)==c);
    }*/

    public static int aMethod(int i)throws Exception
    {
        try{
            return i/10;
        }
        catch (Exception ex)
        {
            throw new Exception("exception in a aMethod");
        }finally{
            System.out.printf("finally");
        }
    }
    public static void main(String[] args){
        double d1=-0.5;
        System.out.println("Ceil d1="+Math.ceil(d1));
        System.out.println("floor d1="+Math.floor(d1));
    }
}
