package day1213.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestZookeeper {
    //connectString字符串中不能有空格，否则不能解析到名称
    private static  String connectString ="hadoop102:2181,hadoop103:2181,hadoop105:2181";
    private static  int sessionTimeOut =2000;
    private ZooKeeper zooKeeper = null;

    @Before
    public void before(){
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("change..");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        try {
            List<String> children = zooKeeper.getChildren("/", true);
            for (String child : children) {
                System.out.println(child);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get(){
        try {
            byte[] data = zooKeeper.getData("/", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    get();
                    System.out.println("改了");
                }
            }, new Stat());


            System.out.println(Arrays.toString(data));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getNow(){
        get();
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
