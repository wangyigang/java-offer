package day1211.OutputFormat;

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

public class MyRecordWriter  extends RecordWriter<LongWritable, Text> {
    private FSDataOutputStream  want =null;
    private FSDataOutputStream other = null;


    public void initlize(TaskAttemptContext job) {
        String path = job.getConfiguration().get(FileOutputFormat.OUTDIR);

        try {
            FileSystem fileSystem = FileSystem.get(job.getConfiguration());
            want = fileSystem.create(new Path(path+"/wang.log"));
            other = fileSystem.create(new Path(path+"/oter.log"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /*
     fsdataoutputFormat
     进行写操作
     */
    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String s = value.toString();
        if(s.contains("atguigu")){
            want.write(value.getBytes());
        }else{
            other.write(value.getBytes());
        }
    }
    /*
    关闭资源操作
     */
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(want);
        IOUtils.closeStream(other);
    }
}
