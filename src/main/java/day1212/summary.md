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


##### 服役新节点
