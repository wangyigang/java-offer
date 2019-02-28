package customerOutputFormat;

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
    FSDataOutputStream other  = null;

    //通过job获取Driver中设置的输出路径
    public void init(TaskAttemptContext job) throws IOException {
        String path = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        FileSystem fileSystem = FileSystem.get(job.getConfiguration());
        //创建fsDataOutputformat
        wang = fileSystem.create(new Path(path + "/wang.log"));
        other = fileSystem.create(new Path(path+"/other.log"));
    }

    //把数据写到文件中
    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String output = value.toString() + "\r\n";
        if(output.contains("wang")){
            wang.write(output.getBytes());
        }else{
            other.write(output.getBytes());
        }
    }

    //关闭资源
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(wang);
        IOUtils.closeStream(other);
    }


}
