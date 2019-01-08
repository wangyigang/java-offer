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
        //如果没有粉丝，直接返回，不进行通知操作
        if(fanscode.isEmpty()){
            return ;
        }
        //通知所有明星的粉丝
        inboxDao.insertMessage(fanscode,starcode, rowkey);
    }

    /**
     * 粉丝关注明星
     * @param fanscode
     * @param starcode
     */
    public void attendStar(String fanscode, String starcode) throws IOException {
        //增加关系--粉丝和明星之间的关系
        relationDao.attendStar(fanscode, starcode);
        //TODO-获取最近发布的5条信息
        List<String> list = weiboDao.scanWeibos(starcode);
        //插入到当前用户的收件箱中
        inboxDao.sendMessages(fanscode,starcode,list);//list中存储的是微博的rowkey

    }

    /**
     * 直接获取明星的微博内容
     * @param starcode
     * @return
     */
    public List<String> getContent(String fanscode,String starcode) throws IOException {
        //先获取inbox中的信息
        List<String> rowkeys= inboxDao.getWeiboRowkeys(fanscode,starcode);
        //查询微博信息
        return weiboDao.getWeibos(rowkeys);

    }

    public void cancleAttent(String fanscode, String starcode) throws IOException {
        relationDao.canleAttent(fanscode,starcode,"attend");
        relationDao.canleAttent(starcode,fanscode,"fans");

        inboxDao.deleteInbox(fanscode,starcode);
    }
}
