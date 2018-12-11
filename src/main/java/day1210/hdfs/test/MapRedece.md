  ####  mapreduce详细工作流程：
  1. 对待处理文本
  2. 进行分片，以128M进行分割
  3. 提交切片信息：job.split  jar  xml
  4. 计算mapTask数量（由rm启动Mr appmaster ）
  5. 每个maptask进行文本处理，inputformat阶段：默认使用TextInputFormat
  默认使用textInputFormat,输出结果为<k,v>
  6. mapper阶段，进行相应逻辑处理，
  7. 写出到缓冲区，默认100M,左侧是索引，kvmeta，右侧是数据,数据达到80%时，逆向进行
  8. 数据进行分区和排序，按照字典顺序进行排序
  9. 溢出到文件（分区且区内有序）
  10. merge归并排序
  11. 全部maptask任务完成后
  12. 启动相应数量的ReduceTask，并告知ReduceTask处理数据范围(数据分区)
  13. 有多少分区，就进行几个ReduceTask，合并文件，归并排序
  14. 一次读一组
  15. 分区
  16. 默认TextOutputFormat
  
  #### Shuffle机制
  定义：map方法之后，reduce方法之前的数据处理
  
  ##### Partition分区
  要求:按照条件输出到不同文件中(分区):
  默认是按照hashcode值进行运算的
  
  ##### 自定义partion分区
  
  