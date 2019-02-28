package customerOutputFormat2;


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


public class FileterRecordWriter extends RecordWriter<LongWritable, Text> {
    FSDataOutputStream wang = null;
    FSDataOutputStream other = null;


    //重写write方法，指定输出路径
    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String s = value.toString() + "\r\n";
        if(s.contains("wang")){
            wang.write(s.getBytes());
        }else {
            other.write(s.getBytes());
        }
    }
    //关闭资源操作
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(wang);
        IOUtils.closeStream(other);
    }

    /**
     *  进行初始化，获取driver中设置路径
     * @param job
     */
    public void init(TaskAttemptContext job) throws IOException {
        //首先获取job设置的路径
        String path = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        //获取文件系统，然后进行开流操作fsdataOutputStream
        FileSystem fileSystem = FileSystem.get(job.getConfiguration());
        //通过fileSystem进行开流
         wang = fileSystem.create(new Path(path+"/wang.log"));
         other = fileSystem.create(new Path(path+"/other.log"));
    }
}
