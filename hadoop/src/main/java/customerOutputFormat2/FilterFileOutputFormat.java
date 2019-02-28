package customerOutputFormat2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FilterFileOutputFormat extends FileOutputFormat<LongWritable, Text> {
    /**
     *  获取recordWriter类，进行写文件操作
     * @param job
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        FileterRecordWriter writer = new FileterRecordWriter();
        writer.init(job);
        return writer;
    }
}
