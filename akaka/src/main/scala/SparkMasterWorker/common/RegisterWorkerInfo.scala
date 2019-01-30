package SparkMasterWorker.common

//具体协议
//样例类--注册的协议：保存id,cpu,ram内存
case class RegisterWorkerInfo(id:String,cpu:Int,ram:Int)
//实体类
class WorkerInfo(val id:String, val cpu:Int, val ram:Int){
  //保存默认的心跳时间--最后的心跳时间
  var lastHeartBeatTime:Long = System.currentTimeMillis()
}

//最后一个类，用于返回注册成功后的信息--object类
case object RegisteredWorkerInfo //单例类

/*
  心跳部分协议
 */
//定时时间到后，发送给自己进行发送sendHeartBeat--自己发送给自己
case object SendHeartBeat
//当定时器发送了一个 SendHeartBeat 消息后，worker 发送一个消息--真正的发送信息--发送给服务器
// (HearBeat(id: String))给 Master
case class HeartBeat(id: String)

/*
    超时心跳部分协议--超过6s就会被删除
 */
//说明
//1.当master启动后，立即发送一个消息 StartTimeOutWorker
//2.当接收到 StartTimeOutWorker， 才启动一个定时器，该定时器每隔10s,发送一个
//RemoveTimeOutWorker 消息
case object StartTimeOutWorker
//RemoveTimeOutWorker, 就去检测哪些worker超时(6s),如果有删除.
case object RemoveTimeOutWorker