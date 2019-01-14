package com.wangyg;


import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 主要用于随机生成随机数，为后面的进行服务--数据收集部分
 */
public class ProductLog {
    //存放联系人电话与姓名的映射
    public Map<String, String> contacts = null;
    //存放联系人电话号码
    public List<String> phoneList = null ;

    /**
     * 初始化：预先存放一些原始数据
     */
    public void initContacts() {
        contacts = new HashMap<>();
        phoneList = new ArrayList<>();

        phoneList.add("15369468720");
        phoneList.add("19920860202");
        phoneList.add("18411925860");
        phoneList.add("14473548449");
        phoneList.add("18749966182");
        phoneList.add("19379884788");
        phoneList.add("19335715448");
        phoneList.add("18503558939");
        phoneList.add("13407209608");
        phoneList.add("15596505995");
        phoneList.add("17519874292");
        phoneList.add("15178485516");
        phoneList.add("19877232369");
        phoneList.add("18706287692");
        phoneList.add("18944239644");
        phoneList.add("17325302007");
        phoneList.add("18839074540");
        phoneList.add("19879419704");
        phoneList.add("16480981069");
        phoneList.add("18674257265");
        phoneList.add("18302820904");
        phoneList.add("15133295266");
        phoneList.add("17868457605");
        phoneList.add("15490732767");
        phoneList.add("15064972307");

        contacts.put("15369468720", "李雁");
        contacts.put("19920860202", "卫艺");
        contacts.put("18411925860", "仰莉");
        contacts.put("14473548449", "陶欣悦");
        contacts.put("18749966182", "施梅梅");
        contacts.put("19379884788", "金虹霖");
        contacts.put("19335715448", "魏明艳");
        contacts.put("18503558939", "华贞");
        contacts.put("13407209608", "华啟倩");
        contacts.put("15596505995", "仲采绿");
        contacts.put("17519874292", "卫丹");
        contacts.put("15178485516", "戚丽红");
        contacts.put("19877232369", "何翠柔");
        contacts.put("18706287692", "钱溶艳");
        contacts.put("18944239644", "钱琳");
        contacts.put("17325302007", "缪静欣");
        contacts.put("18839074540", "焦秋菊");
        contacts.put("19879419704", "吕访琴");
        contacts.put("16480981069", "沈丹");
        contacts.put("18674257265", "褚美丽");
        contacts.put("18302820904", "孙怡");
        contacts.put("15133295266", "许婵");
        contacts.put("17868457605", "曹红恋");
        contacts.put("15490732767", "吕柔");
        contacts.put("15064972307", "冯怜云");
    }

    //将随机产生的日志写入到本地文件中callLog中
    public void writeLog(String filePath, ProductLog productLog){
        //通过IO流的方式写入到文件
        OutputStreamWriter outputStreamWriteer = null;

        try {
            //filepath--指定输出路径,boolean:控制是否追加，  指定编码格式
            outputStreamWriteer = new OutputStreamWriter(new FileOutputStream(filePath,true),"utf-8");

            while (true){
                String log = productLog.productLog();
                outputStreamWriteer.write(log + "\n");
                outputStreamWriteer.flush();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于生产数据
     * @return
     */
    private String productLog() {
        int call1Index = new Random().nextInt(phoneList.size());
        int call2Index = -1;

        String call1 = phoneList.get(call1Index);
        String call2 = null;
        while (true) {
            call2Index = new Random().nextInt(phoneList.size());
            call2 = phoneList.get(call2Index);
            if (!call1.equals(call2)) break;
        }
        //随机生成通话时长(30分钟内_0600)
        int duration = new Random().nextInt(60 * 30) + 1;

        //格式化通话时间，使位数一致
        String durationString = new DecimalFormat("0000").format(duration);
        //通话建立时间:yyyy-MM-dd,月份：0~11，天：1~31
        String randomDate = randomDate("2019-01-01", "2020-01-01");
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(randomDate));

        //拼接log日志
        StringBuilder logBuilder = new StringBuilder();
        //数据格式 call1,call2,日期,通话时间
        logBuilder.append(call1).append(",").append(call2).append(",").append(dateString).append(",")
                .append(durationString);
        System.out.println(logBuilder);
        try {
            Thread.sleep(500);//进行休眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return logBuilder.toString();
    }

    //随机生成通话时间
    //随机生成通话建立时间
    private String randomDate(String startDate, String endDate) {
        //simpleDateFormat 进行格式化--以年--月--日期
        SimpleDateFormat simpleDateFordmat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = simpleDateFordmat.parse(startDate);
            Date end = simpleDateFordmat.parse(endDate);
            //防御性编程
            if (start.getTime() > end.getTime()) return null;
            //
            long resultTime = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
            return resultTime + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //进行测试
    /**
     * 测试产生的数据
     * @param args
     */
    public static void main(String[] args) {
        //args = new String[]{"D:\\log.csv"};
        if(args == null || args.length <= 0) {
            System.out.println("no arguments");
            System.exit(1);
        }
        ProductLog productLog = new ProductLog();
        productLog.initContacts();
        productLog.writeLog(args[0], productLog);
    }
}
