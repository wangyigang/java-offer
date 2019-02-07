package month02.day02;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient {
    private FileSystem fs = null;

    @Test
    public void testMkdir() throws URISyntaxException, IOException, InterruptedException {
        //获取文件系统
        Configuration conf = new Configuration();
        //配置在集群上运行
        conf.set("fs.defaultFS", "hdfs://hadoop201:9000");

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop201:9000"), conf, "wangyg");

        // 2 创建目录
        fs.mkdirs(new Path("/wangyg/pangdi"));

        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testCopyFromLocalFile() throws IOException, URISyntaxException, InterruptedException {
        //获取文件系统
        Configuration conf = new Configuration();
        conf.set("dfs.replication", "2"); //设置副本个数
        //获取文件系统
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop201:9000"), conf, "wangyg");
        //上传文件
        fileSystem.copyFromLocalFile(new Path("C:\\Users\\wangyg\\Desktop\\TODO.txt"),
                new Path("/wangyg"));

        //上传完毕后，关闭资源
        fileSystem.close();

        System.out.println("上传完毕...");
    }

    //文件下载
    @Test
    public void testCopyToLocalFile() throws URISyntaxException, IOException, InterruptedException {
        //获取文件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop201:9000"),
                conf, "wangyg");
        fs.copyToLocalFile(false, new Path("/wangyg/TODO.txt")
                , new Path("D:\\"));

        System.out.println("下载完成...");

    }

    //文件夹删除
    @Test
    public void testDelete() throws URISyntaxException, IOException, InterruptedException {
        //获取配置
        Configuration conf = new Configuration();
        //获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop201:9000"), conf, "wangyg");
        boolean delete = fs.delete(new Path("/wangyg/"), true);
        if (delete) {
            System.out.println("删除成功...");
        }
        //关闭资源
        fs.close();

    }

    @Before
    public void before() throws URISyntaxException, IOException, InterruptedException {
        //获取配置
        Configuration conf = new Configuration();
        //获取文件系统
        fs = FileSystem.get(new URI("hdfs://hadoop201:9000"), conf, "wangyg");
    }

    //HDFS文件名更改
    @Test
    public void testRename() throws IOException {
        boolean rename = fs.rename(new Path("/1.txt"), new Path("/2.txt"));

        if (rename) {
            System.out.println("修改名称成功...");
        }
        //关闭资源
        fs.close();
    }

    //HDFS文件详情查看
    @Test
    public void testListFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus next = listFiles.next();
            //文件名称--输出详情
            System.out.println(next.getPath().getName());
            //长度
            System.out.println(next.getLen());
            //权限
            System.out.println(next.getPermission());
            //获取分组
            System.out.println(next.getGroup());

            //获取存储的块信息
            BlockLocation[] blockLocations = next.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                //获取块存储的主节点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }
        //关闭资源
        fs.close();

    }

    //hdfs文件和文件夹判断
    @Test
    public void testListStatus() throws IOException {
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            //如果是文件
            if(fileStatus.isFile()){
                System.out.println("f:"+fileStatus.getPath().getName());
            }else{//文件夹
                System.out.println("d:"+fileStatus.getPath().getName());
            }
        }
    }
}
