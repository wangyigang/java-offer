package etl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class ETLDirver  implements Tool {
    private Configuration conf = null;
    @Override
    public int run(String[] args) throws Exception {
        //进行属性设置
        conf.set("inputPath", args[0]);
        conf.set("outputPath", args[1]);

        Job job = Job.getInstance(conf);
        job.setJarByClass(ETLDirver.class);
        job.setMapperClass(ETLMapper.class);
        job.setNumReduceTasks(0);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //封装两个函数进行设置fileinputformat fileoutputformat
        initInputFortmat(job);
        initmyOutputFormat(job);
        boolean b = job.waitForCompletion(true);
        return b? 0:1;

    }

    //设置输出路径方法
    private void initmyOutputFormat(Job job) throws IOException {
        String outputString = conf.get("outputPath");
        Path path = new Path(outputString);
        FileSystem fileSystem = FileSystem.get(conf);
        if(fileSystem.exists(path)){
            //如果存在删除
            fileSystem.delete(path, true);//递归删除
        }
        FileOutputFormat.setOutputPath(job, path);
    }
    //设置输入路径方法
    private void initInputFortmat(Job job) throws IOException {
        //获取shu
        String outputString = conf.get("inputPath");
        Path path = new Path(outputString);

        //获取filesystem
        FileSystem fileSystem = FileSystem.get(conf);
        //如果存在
        if(fileSystem.exists(path)){
            //设置job
            FileInputFormat.setInputPaths(job, path);
        }

    }

    //设置conf
    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    //获取conf
    @Override
    public Configuration getConf() {
        return this.conf;
    }

    public static void main(String[] args) {
        //args = new String[]{"D:\\input\\video","D:\\input\\video\\output"};
        try {
            ToolRunner.run(new ETLDirver(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
