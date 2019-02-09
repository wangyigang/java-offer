package WholeFileInputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class WholeFileInputDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 输入输出路径需要根据自己电脑上实际的输入输出路径设置
        args = new String[] { "d:/input/inputinputformat", "d:/output1" };

        //获取job实例
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置类路径
        job.setJarByClass(WholeFileInputDriver.class);
        //设置inputformat

        job.setInputFormatClass(WholeFileInputFormat.class);
        //设置outputformat
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        //设置mapper和Reducer
        job.setMapperClass(WholeFileInputMapper.class);
        job.setReducerClass(WholeFileInputReducer.class);

        //设置map和reduce输出格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 6 提交job
        boolean result = job.waitForCompletion(true);
        System.out.println(result);
        System.exit(result ? 0 : 1);
    }
}
