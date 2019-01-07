package controller;


import service.WeiboService;

import java.io.IOException;

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

    public void checkStarContent(String starcode) {

    }

    public void cancleAttent(String fanscode, String starcode) {

    }
}
