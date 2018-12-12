package day1212.TogetherFriend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
1．需求
以下是博客的好友列表数据，冒号前是一个用户，冒号后是该用户的所有好友（数据中的好友关系是单向的）
求出哪些人两两之间有共同好友，及他俩的共同好友都有谁？
共同好友： A 有C
          B 有C

先拆分成 A:B  A:C A:D A:F  A:E  A:O
A	I,K,C,B,G,F,H,O,D,
B	A,F,J,E,
C	A,E,B,H,F,G,K,
D	G,C,K,A,L,F,E,H,
E	G,M,L,H,A,F,B,D,
F	L,M,D,C,G,A,
G	M,
H	O,
I	O,C,
J	O,
K	B,
L	D,E,
M	E,F,
O	A,H,I,J,F,


 */
public class TogetherFriendDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取job和configuration
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TogetherFriendDriver.class);

        job.setMapperClass(TogetherFriendMapper.class);
        job.setReducerClass(TogetherFriendReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\input\\friend/friends.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\input\\friend\\output"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b);
    }
}
