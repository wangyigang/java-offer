package mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MapjoinMapper extends Mapper<LongWritable, Text, Text,NullWritable> {
    private Map<String, String> m = new HashMap<>();

    private Text k =new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //将map进行缓存到内存中
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath().toString();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

        String line;
        while(StringUtils.isNotEmpty(line=reader.readLine())){
            //切割
            String[] split = line.split("\t");
            m.put(split[0], split[1]);
        }
        //读完后
        reader.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();

        //获取
        String[] split = line.split("\t");

        //获取产品Id
        String pid = split[1];

        //获取商品名称
        String name = m.get(pid);

        //拼接字符串
        k.set(line+"\t" + name);
        //写出
        context.write(k, NullWritable.get());
    }
}
