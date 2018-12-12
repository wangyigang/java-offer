package day1212.TopN;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TopFlowDriver {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(TopFlowDriver.class);

            job.setMapperClass(TopFlowMapper.class);
            job.setReducerClass(TopFlowReducer.class);

            job.setMapOutputKeyClass(TopFlowBean.class);
            job.setMapOutputValueClass(Text.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(TopFlowBean.class);

            job.setGroupingComparatorClass(TopNGroupingComparator.class);

            FileInputFormat.setInputPaths(job, new Path("D:\\input\\top/top10input.txt"));
            FileOutputFormat.setOutputPath(job, new Path("D:\\input\\top/output"));

            boolean b = job.waitForCompletion(true);
            System.out.println(b);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
