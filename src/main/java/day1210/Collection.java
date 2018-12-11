package day1210;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

/*
mapreduce工作流程：
1.将待处理文本进行切片：切片，获取配置信息
2.将切片文件job.split 和jar包（自己写的程序） job.xml（配置文件）提交给yarn 的RM
3.根据切片的个数决定启动几个MapTask.yarn会调用RMresourceManager,创建一个 MR appmaster
(job的资源调度老大)，会根据切片信息，决定启动几个mapTask
4.调用inputformat 对数据进行处理,默认是TextInputFormat(TextInputFormat分两步: 读取行信息，数据进行转换成 <k,v>方式)
5.经过map<k,v>经由context.write(k,v)写出去
6.写出去的<k,v>值会被outputCollector收集，写入环形缓冲区
7.环形缓冲区默认100MB ,数据达到80%后溢写，然后反向往回写
8.进行分区和排序 （快排）--每次分区和排序后 会进行一次conbiner(合并)
9.溢写到文件中(分区且分区内有序)(多次的溢写到文件中)
10:merger归并排序(10个一组进行合并)
11. 启动相应数量的ReduceTask,
12> 下载到ReduceTask本地磁盘
13.合并文件，归并排序
14.一次读取一组，传给Reducer
15. groupingComparator 自定义分组

共发生一次快速排序， 三次归并排序
 */

/*
partition分区：
ReduceTask：手动设置的

 */

/*
序列化：
把内存中的对象，转换成字节序列，以便持久化或网络传输

反序列化：将字节序列转换成java对象
 */

/*
切片机制与mapTask并发度机制
为什么要128M
答：Block size为128MB,
 */
public class Collection {
    public static void main(String[] args) {

    }
}
