package com.example.totallearn;

import com.blankj.utilcode.util.StringUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shinelon on 2020/6/17.
 */

public class Test1 {
    @Test
    public void t1() {
        Singleton1.getInstance().base = "111111111111";
    }

    @Test
    public void test2() {
        String[] pxp = new String[]{"aaaa", "bbbbb", "ccccc"};

        String dad = "";
        for (int i = 0; i < pxp.length; i++) {
            dad = dad + "," + pxp[i];
        }
        dad = dad.substring(1);
        System.out.println(dad);
    }

    @Test
    public void test3() {
        String t1 = "ac";
        t1 = t1.substring(0, 1);
        System.out.println(t1);
    }

    @Test
    public void test4() {
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("List转字符串");
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        String ss = String.join(",", list1);
        System.out.println(list1.toString());
//        System.out.println(StringUtils.join("",list1));
        System.out.println(ss);
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("字符串转List");
        List<String> listString = Arrays.asList(ss.split(","));
        for (String string : listString) {
            System.out.println(string);
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
    }

    @Test
    public void test5() {
        int i = 10;
        if (i > 9) {
            System.out.println("判断9");
        } else if (i > 8) {
            System.out.println("判断8");
        }
    }
}
