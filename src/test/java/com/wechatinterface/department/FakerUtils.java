package com.wechatinterface.department;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakerUtils {
    private final static int delta = 0x9fa5 - 0x4e00 + 1;
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,188,185,181".split(",");

    // lengh：获取的随机的长度
    //获取指定长度的随机数
    public static int getRandomInt(int lengh) {

        lengh = lengh - 1;
        int randomint = (int) ((Math.random() * 9 + 1) * Math.pow(10, (double) lengh));
        return randomint;
    }
    //min：获取的随机数的左边界， max：获取随机数的右边界
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
    //获取时间戳
    public static String getTimeStamp(){
        return  String.valueOf(System.currentTimeMillis());
    }

    public static int getNum(
            int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
    //电话号码生成器
    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + thrid;
    }
    //读取数据文件
    public static Object[][] getTestData(String fileName) throws IOException {
        String projectRoot = new File("").getAbsolutePath();
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        //设置字符集为UTF-8
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(projectRoot + fileName), "UTF-8"));
        //忽略CSV文件的标题行（第一行）
        file.readLine();
        //遍历读取文件中除第一行外的其它所有行内容，并存储在名为records的ArrayList中，每一个records中存储的对象为一个string数组；
        while ((record = file.readLine()) != null) {
            String fields[] = record.split(",");
            records.add(fields);
        }
        //关闭文件
        file.close();
        //定义函数返回值Object[][],将list转换为一个Object的二维数组；
        Object[][] results = new Object[records.size()][];
        //设置二维数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }
}
