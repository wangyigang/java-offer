import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {


    //zookeeper集群节点string
    private final String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;


    public static void main(String[] args) {
        DistributeServer server = new DistributeServer();

        //连接zookeeper集群
        try {
            server.getConnect(); //获取zookeeper连接

            //进行注册
            server.regist(args[0]);

            //3.业务逻辑处理
            server.business();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);

    }

    private void regist(String hostname) throws KeeperException, InterruptedException {
        zkClient.create("/servers/server",
                hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostname+"is online...");

    }

    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {

                    }
                });

    }
}
