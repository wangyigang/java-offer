snappy 压缩学习 如何使用
sequencefile
数据倾斜案例：

##### NN与2NN工作机制：
1.namenode启动时，先加载fsimage edits log
2.client客户端发送请求时，先将请求记录写在edits log中，在同步到内存中，（安全方面考虑）
    namenode不断进行记录日志
    
3.2NN每隔一分钟进行checkpoint,查询是否需要进行checkpoint
    checkpoint条件：每隔一小时
                   edits log达到100万
4.如果达到条件,namenode将当前编辑的edits log 停止写入，重新开辟一个edits_inprocess, 将fsimage 
和edits log 传给2NN,将两个进行合并，合并后的在传给namenode

##### DataNode工作机制
datanode启动后，首先向namnode进行注册
namenode返回注册成功即可
datanode周期性的向namenode反馈存储的块信息，当前DataNode存储了那些块

datanode和namenode每三秒进行一次心跳反应。心跳带有namenode传给DataNode的明林
如果datanode和namenode之间超过10分钟30秒没有成功通信，则证明死亡

###### 10分钟30秒由来：
dfs.namenode.heartbeat.recheck-interval:5分钟
dfs.heartbeat.interval :3
timeout = 2* dfs.namenode.recheck-interval + 10* dfs.heartbeat.interval


##### Mapreduce开发总结
输入数据接口：inputFormat
默认使用TextInputFormat
1.textInputFormat功能是一次读取一行数据，行起始位置偏移量为key, 行内容为value 进行返回
2.keyValueTextInputFormat每一行均为一个记录，默认分隔符为tab "\t",分给为key,value形式
3.NlineInputFormat 按照指定的行数N来切分
4.CombineTextInputFormat可以合并多个小文件，提高处理效率
5.自定义InputFormat


###### 逻辑处理接口：mapper
根据业务需要处理实现其中的三个方法：map() setup() cleanup()

###### Partitioner分区
默认是HashPartitioner 按照hashcode()进行切分
切分源码是: (key.hashCode() & INT.MAX_VALUE) %numReduces 

reduceTask数量需要手动设置， 默认为1

comparable排序：实现WritableComparable接口，重写其中compareTo方法

Combiner合并：
Combiner合并可以提高程序执行效率，减少IO传输，但是使用时不能影响原有业务逻辑

##### Hadoop数据压缩
有效减少hdfs读写字节数，压缩提高了网络和磁盘空间的效率，在数据规模很大和工作
负载密集的情况下，因此，使用数据压缩显得非常重要
数据压缩可以在任意MapReduce阶段启用压缩，但是还是有一些代价
压缩是提高hadoop运行效率的一种优化策略
通过对Mapper， Reducer运行过程的数据进行压缩，以减少磁盘IO，提高mr程序运行时速度
注意：采用压缩技术减少了IO,但是会给CPU增加负担，所以压缩运用得当会提高性能，运用不得当
会降低性能。
 
 ---
 压缩可以在数据IO密集，CPU负担较小的情况下进行使用
 ---

MR支持的压缩编码
LZO和Snappy
压缩位置选择： 可以在MapReduce作用的任意阶段启用

##### Yarn 资源调度器
yarn是一个资源调度平台，负责为运算程序提供服务器运算资源，相当于一个
分布式的操作系统平台，而mapreduce等运算程序相当于运行在操作系统上的应用程序
###### yarn基本架构
yarn主要由resourceManager,NodeManager, ApplicationMaster 
RM
NM:管理单个节点上的资源  处理来自RM的命令， 处理来自APPMaster的命令
APPMaster ：负责数据的切分，为应用程序申请资源并分配给内部非任务
            任务的监控与容错
       
Container:资源对象的封装

##### yarn工作机制
程序提交到客户端所在节点
先向resourceManager申请一个application 
rm返回资源提交路径和application_id
客户端将资源提交到HDFS上
资源提交完毕后，申请运行mrAppmaster
将提交申请封装成一个task
task分配到一个nodemanager后，
创造容器Container
下载job资源到本地
申请运行mapTask容器 
新分配两个nodemanager进行处理任务，创建容器
appmaster进行发送所需运行脚本 map阶段完成后
appmaster又想rm申请两个容器，进行运行ReduceTask程序
两个reduceTask任务完成后，
APPmaster注销自己，完成使命


资源调度器
FIFO CAPACITY FAIR
FIFO:先进先出
capacity Scheduler:默认使用capacity Scheduler
```
支持多个队列，每个队列可配置一定的资源量，每个队列采用FIFO调度策略
为了防止同一个
```

任务推测执行：

