/**
 * 51diancai.com Inc.
 * Copyright (c) 2011-2011 All Rights Reserved.
 */
package com.loiot.baqi.utils;

import java.util.Random;

/**
 * @Id $Id: RandomUtil.java 1334 2011-12-26 14:38:10Z yulianyu $
 * @author yulianyu
 */
public class RandomUtil {

    public static String getRandomNum(int length,long... seed) {
        java.util.Random rand = null; // 设置随机种子
        if(seed.length>0){
            rand = new Random(seed[0]);
        } else {
            rand = new Random();
        }
        
        String base = "0123456789";
        int size = base.length();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int start = rand.nextInt(size);
            String tmpStr = base.substring(start, start + 1);

            str.append(tmpStr);
        }
        return str.toString();
    }
    
    
    public static String getRandomStr(int length,long... seed) {
        java.util.Random rand = null; // 设置随机种子
        if(seed.length>0){
            rand = new Random(seed[0]);
        } else {
            rand = new Random();
        }
        String base = "abcdefghjkmnpqrstuvwxyz23456789";
        int size = base.length();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int start = rand.nextInt(size);
            String tmpStr = base.substring(start, start + 1);

            str.append(tmpStr);
        }

        return str.toString();
    }
    
    public static void main(String[] args) {
      System.out.println(); 

	}
}
