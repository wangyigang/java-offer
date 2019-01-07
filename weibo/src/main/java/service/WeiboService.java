package service;

import dao.AdminDao;
import dao.InboxDao;
import dao.RelationDao;
import dao.WeiboDao;

import java.io.IOException;
import java.util.List;

/**
 * 第二层：service层
 *
 */
public class WeiboService {
    private AdminDao adminDao = new AdminDao();// 管理员DAO，专门管理表的创建

    private InboxDao inboxDao = new InboxDao(); //消息表
    private RelationDao relationDao = new RelationDao();// 关系表
    private WeiboDao weiboDao = new WeiboDao();// 微博表


    public void createNameSpace() throws IOException {
        //创建名称空间
        adminDao.createNameSpace();

    }

    public void createTables() throws IOException {
        inboxDao.createTable();
        relationDao.createTable();
        weiboDao.createTable();
    }
    /*
    明星发布微博
     */
    public void publishWeibo(String starcode, String content) throws IOException {
       String rowkey =  weiboDao.publishWeibo(starcode,content);
        //获取明星的所有粉丝
        List<String> fanscode = relationDao.getAllfans(starcode);
        //通知所有明星的粉丝
        inboxDao.insertMessage(fanscode, rowkey);
    }

    /**
     * 粉丝关注明星
     * @param fanscode
     * @param starcode
     */
    public void attendStar(String fanscode, String starcode) throws IOException {
        relationDao.attendStar(fanscode, starcode);
    }
}
