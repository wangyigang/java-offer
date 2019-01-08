import controller.WeiboController;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class WeiboTest {
    public static void main(String[] args)  {
        try {

            //1.导入pom依赖文件

            //建立三层架构 dao service controller

            //初始化--创建三张hbase表结构--微博表-明星表 & 粉丝表（关系)  //收件箱表
            WeiboController controller = new WeiboController();
            controller.init(); //初始化操作
            System.out.println("初始化完成...");

            // 3) 明星（zhangsan）发布微博
            String starcode = "dilireba";
            String fanscode = "wangyg-b";
            String content1 = "hello dear!!!"+"-1";
            String content2 = "hello dear!!!"+"-2";
            String content3 = "hello dear!!!"+"-3";

            //发送消息
            controller.publishWeibo(starcode, content1);
            controller.publishWeibo(starcode, content2);
            controller.publishWeibo(starcode, content3);
            System.out.println("微博发布成功...");


            // 4) 普通用户(lisi)关注明星(zhangsan)
            controller.attendStar(fanscode, starcode);
            System.out.println("关注明星完成...");

            // 5) 粉丝（lisi）查询明星(zhangsan)的微博内容
            controller.checkStarContent(fanscode, starcode);
            // 6) 粉丝(lisi)取消关注明星（zhangsan）
            //取消关注双方的信息，粉丝和明星两个关系都要处理
            controller.cancleAttent(fanscode,starcode);
            //删除粉丝的收件箱的信息


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
