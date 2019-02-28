package customerOutputFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FilterOutputFormat  extends FileOutputFormat<LongWritable, Text> {


    //实现recordWriter方法
    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {

        FileterRecordWriter record = new FileterRecordWriter();
        record.init(job);
        return record;
    }
}
