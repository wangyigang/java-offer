package Hbase_Mapreduce2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;

public class HbaseFileHiveMrTools implements Tool {
    @Override
    public int run(String[] args) throws Exception {
       Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        //设置job的类路径
        job.setJarByClass(HbaseFileHiveMrTools.class);
        //设置mapper阶段
        job.setMapperClass(HBaseHDFSFileMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        //设置reduce阶段

        TableMapReduceUtil.initTableReducerJob("fruit_mr",
                HbaseFileHiveReducer.class,
                job);


        //设置map参数
        FileInputFormat.setInputPaths(job,
                new Path("hdfs://hadoop102:9000/input_fruit/fruit.tsv"));

        boolean b = job.waitForCompletion(true);

        return b? JobStatus.State.SUCCEEDED.getValue():JobStatus.State.FAILED.getValue();
    }

    @Override
    public void setConf(Configuration conf) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
