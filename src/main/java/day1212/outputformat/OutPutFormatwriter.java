package day1212.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OutPutFormatwriter extends RecordWriter<LongWritable, Text> {
    private FSDataOutputStream owant = null;
    private FSDataOutputStream oother = null;

    /*
    初始化
    两个IO流:

     */
    public void initialize(TaskAttemptContext job) {
        //获取路径
        String path = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        Configuration conf = job.getConfiguration();
        //获取文件系统
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            owant = fileSystem.create(new Path(path+"/want"));
            oother = fileSystem.create(new Path(path+"/other"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String line = value.toString();
        if(line.contains("atguigu")){
            owant.write(line.getBytes());
        }else{
            oother.write(line.getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(owant);
        IOUtils.closeStream(oother);
    }
}
