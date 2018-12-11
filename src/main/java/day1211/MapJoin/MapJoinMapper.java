package day1211.MapJoin;

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

public class MapJoinMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Map<String, String> stringMap = new HashMap<>();
    private Text text = new Text();

    /*
    setup：设置job
    mapJoin:需要将一张表读取到内存中， 另一张表从文件读写.
    所以需要将一张小表设置为缓存，将其读取到内存中

     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] files = context.getCacheFiles();
        URI uri = files[0];
        //TODO
        //开启IO流读取数据以KV值方式存储
        BufferedReader bufferedReader = null;
        try{
             bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(uri.getPath().toString())));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                String[] split = line.split("\t");
                stringMap.put(split[0], split[1]);
            }
        }finally {
            if(bufferedReader!= null){
                bufferedReader.close();
            }
        }


    }

    /*
    map阶段:直接将数据处理，不用经过reduce阶段
    直接一行一行读取数据，然后获取需要的数据，通过map获取对应的数据，然后进行拼接
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //id	pid	amount
        String[] fiels = value.toString().split("\t");
        String out = fiels[0] + "\t" + stringMap.get(fiels[1]) +"\t"+ fiels[2];
        text.set(out);
        context.write(text, NullWritable.get());
    }
}
