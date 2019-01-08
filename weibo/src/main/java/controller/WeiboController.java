package controller;


import dao.RelationDao;
import service.WeiboService;

import java.io.IOException;
import java.util.List;

/**
 * 三层架构
 * 第一层：controller
 * 第二层：service
 * 第三层： DAO层
 */
public class WeiboController {
    //成员变量
    private WeiboService weiboService = new WeiboService();

    /**
     * hbase表结构初始化
     */
    public void init() throws IOException {
        //创建名称空间
        weiboService.createNameSpace();
        //创建表
        weiboService.createTables();
    }

    /**
     * 发布微博--存储的格式直接是starcode_时间戳,利用时间戳标明，以防覆盖
     * @param starcode
     * @param content
     */
    public void publishWeibo(String starcode, String content) throws IOException {
        try {
            // 发微博
            weiboService.publishWeibo(starcode, content);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * 添加关注明星
     * @param fanscode
     * @param starcode
     */
    public void attendStar(String fanscode, String starcode) throws IOException {
        weiboService.attendStar(fanscode,starcode);
    }

    /**
     *  查询明星的微博信息
     * @param starcode
     */
    public void checkStarContent(String fanscode,String starcode) throws  IOException {
        //查取收件箱中的微博标识 rowkey
        List<String> contents = weiboService.getContent(fanscode, starcode);
        if(contents.isEmpty()){
            System.out.println("明星"+starcode+"无最新数据...");
        }
        System.out.println("------------------");
        for (String content : contents) {
            System.out.println(content);

        }
        System.out.println("-------------------");
    }

    /**
     *  取消关注明星和分析
     * @param fanscode
     * @param starcode
     */
    public void cancleAttent(String fanscode, String starcode) throws IOException {
        weiboService.cancleAttent(fanscode,starcode);
    }
}
