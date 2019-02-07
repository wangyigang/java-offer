package month02.day03.FlowCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{};

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置类路径
        job.setJarByClass(FlowCountDriver.class);
        //指定本业务job要使用的map
        job.setMapperClass(FlowCountMapper.class);
        //指定job的reduce类
        job.setReducerClass(FlowCountReducer.class);
        //设置map阶段输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //设置reduce阶段输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //设置job输入路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //设置job的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //提交Job,并运行
        boolean b = job.waitForCompletion(true);
        System.out.println(b);

    }
}
