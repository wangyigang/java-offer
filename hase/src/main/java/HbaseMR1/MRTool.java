package HbaseMR1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

public class MRTool implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        //获取configuration
        Configuration conf = new Configuration();
        //获取job
        Job job = Job.getInstance(conf);

        Scan scan = new Scan();
        //mapper--scan--for循环遍历，然后封装成put--hbase提供了工具类
        TableMapReduceUtil.initTableMapperJob(
                "fruit",
                scan,
                HbaseMRMapper.class,
                ImmutableBytesWritable.class, Put.class,job);

        //reudece
        TableMapReduceUtil.initTableReducerJob("fruit_mr",HBaseMRReduce.class
                , job);

        //verbose: boolean变量，是否为用户打印详细信息-- 向用户详细打印进度
        boolean b =job.waitForCompletion(true);
        return b? JobStatus.State.SUCCEEDED.getValue(): JobStatus.State.FAILED.getValue();
    }

    /**
     *  设置conf--目前不需要
     * @param conf
     */
    @Override
    public void setConf(Configuration conf) {

    }

    /**
     *  获取conf--目前不需要
     * @return
     */
    @Override
    public Configuration getConf() {
        return null;
    }
}
