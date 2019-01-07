package HbaseMRtool;

import HbaseMRMapper.HbaseMapper;
import HbaseMRReduce.HbaseMRReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

public class HbaseMRTools implements  Tool{

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf  =new Configuration();
        Job job = Job.getInstance(conf);

        //设置类路径
        job.setJarByClass(HbaseMRTools.class);
        //设置map
        Scan scan = new Scan();
        TableMapReduceUtil.initTableMapperJob("fruit",  //表名
                scan, //扫描全表的方式
                HbaseMapper.class, //mapper的class
                ImmutableBytesWritable.class, //key输出类型
                Put.class, //value的输出类型
                job);

        //设置输出
        //TableMapReduceUtil.initTableReducerJob("fruit_mr", HbaseMRReduce.class, job);
        TableMapReduceUtil.initTableReducerJob("fruit_mr",
                HbaseMRReduce.class,//reducer .class
                job);


        boolean b = job.waitForCompletion(true);

        return b?JobStatus.State.SUCCEEDED.getValue():JobStatus.State.FAILED.getValue();
    }

    @Override
    public void setConf(Configuration conf) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
