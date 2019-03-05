package HbaseMR2;


import HbaseMR1.HBaseMRReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

public class HBaseFileTool implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        //获取配置信息
        Configuration conf = new Configuration();
        //获取job
        Job job = Job.getInstance(conf);
        //设置map端操作
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        job.setMapperClass(HbaseFileMapper.class);
        //设置reduce端操作

        TableMapReduceUtil.initTableReducerJob("fruit_mr",HBaseMRReduce.class
                , job);
        //提交job
        boolean b = job.waitForCompletion(true);
        //输出结果
        System.out.println(b);
        return b? JobStatus.State.SUCCEEDED.getValue(): JobStatus.State.FAILED.getValue();
    }

    /**
     *  设置配置信息--目前不需要
     * @param conf
     */
    @Override
    public void setConf(Configuration conf) {

    }

    /**
     *  获取配置信息，目前不需要
     * @return
     */
    @Override
    public Configuration getConf() {
        return null;
    }
}
