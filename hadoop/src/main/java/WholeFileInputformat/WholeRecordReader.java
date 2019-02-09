package WholeFileInputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class WholeRecordReader extends RecordReader<Text, BytesWritable> {
    //标记位--flag为true,表示文件没有去读，false-表示读完
    private boolean flag = true;
    private Configuration conf=null;
    private FileSplit split = null;
    private Text k = new Text();
    private BytesWritable v = new BytesWritable();
    private FSDataInputStream fs ;
    /**
     *  初始化方法--框架会自动调用一次
     * @param split
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        //获取文件切片--强转
        this.split = (FileSplit) split;
        //获取配置信息
        this.conf = context.getConfiguration();
        //读取数据--开流准备
        Path path =(this.split).getPath();
//        FileSystem fileSystem = FileSystem.get(context.getConfiguration());
        FileSystem fileSystem = path.getFileSystem(context.getConfiguration());
        this.fs = fileSystem.open(path);

    }

    /**
     * 核心方法--读取下一组key value值
     * @return 读到返回true,否则返回false
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(flag){
            //读取文件
            byte[] bytes = new byte[(int) split.getLength()];
            fs.read(bytes);
            String path = split.getPath().toString();
            v.set(bytes, 0, bytes.length);
            k.set(path);

            flag =false;
            return true;
        }

        return false;
    }

    /**
     *  获取当前key
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return k;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return v;
    }

    /**
     *  获取当前进度
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return flag? 0:1;
    }

    /**
     * 关闭方法
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        IOUtils.closeStream(fs);
    }
}
