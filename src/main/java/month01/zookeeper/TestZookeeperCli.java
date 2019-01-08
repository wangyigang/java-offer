package month01.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("all")
public class TestZookeeperCli {
    private static final String connectString = "hadoop102:2181,hadoop103:2181,hadoop105:2181";
    //将zookeeper客户端对象提出，变为成员变量
    ZooKeeper zooKeeper = null;

    //@before
    @Before
    public void test() throws IOException {
        zooKeeper = new ZooKeeper(connectString,
                2000,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        //通过event参数可以获取
                        System.out.println("changed....." + event.getPath()
                                + event.getState()
                                + event.getType()
                        );
                    }
                });
    }

    /**
     * 显示某路径下所有数据 ls
     */
    @Test
    public void testls() {
        try {
            //可以增加监听方式，如果新增了一个watcher()，就不会用调用zookeeper中的
            List<String> children = zooKeeper.getChildren("/", new Watcher() {
                //注册一次，使用一次就完成，如果希望一直监听，需要递归进行注册
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("根目录发生变化");
                }
            });

            //查找/路径，是否watch是否监听
            for (String child : children) {
                System.out.println(child);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //sleep() --静态方法，调用对象是当前main主线程
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归进行注册方式，一直进行监听路径下状态
     */
    @Test
    public void get() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/test", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //先发送消息，进行通知,在进行递归注册
                System.out.println("/test 发生改变...");
                try {
                    get();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());

        System.out.println(data.toString());
    }

    @Test
    public void getNow() {
        try {
            get();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //判断是否存在
    @Test
    public void testexist() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/test", true);
        System.out.println(stat);

    }
}
