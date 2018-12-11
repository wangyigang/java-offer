package day1211.MapJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapJoinDriver {
    public static void main(String[] args) {
        //获取configuration 和job
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(MapJoinDriver.class);

            job.setMapperClass(MapJoinMapper.class);
            job.setNumReduceTasks(0);
            //设置缓存
            //TODO
            job.addCacheFile(new URI("file:///d:/input/mapjoin/pd.txt"));
            //设置输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            //设置输入输出
            FileInputFormat.setInputPaths(job, new Path("d:/input/mapjoin/order.txt"));
            FileOutputFormat.setOutputPath(job, new Path("d:/input/mapjoin/output"));
            //提交
            boolean b = job.waitForCompletion(true);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
