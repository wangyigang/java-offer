package SparkMasterWorker.master

import SparkMasterWorker.common._
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.mutable
import scala.concurrent.duration._ //用于引入mills 时间计时单位的包


/*
功能三：定时任务每隔10s,将心跳超过6s的删除掉
 */
class SparkMaster extends Actor {
  var registMap = mutable.HashMap[String, WorkerInfo]()

  //实现receive方法
  override def receive: Receive = {
    //case--模式匹配，匹配各种情况，然后针对各种情况进行处理
    case "start" => {
      println("spark master启动，开始监控...")

      //进行自己的定时删除任务
      self ! StartTimeOutWorker //首先，自己会给自己发送一个定时任务
    }
    //匹配worker向master发送注册时间的情况
    case RegisterWorkerInfo(id, cpu, ram) => {
      //向hashMap中添加信息
      if (!registMap.contains(id)) {
        // 如果不存在，原容器中不存在
        registMap += (id -> new WorkerInfo(id, cpu, ram))
        println("添加成功。。。。。。")
        //容器中添加成功后，向原发送者发送成功消息
        sender() ! RegisteredWorkerInfo
      }
    }
      //用于处理心跳相关逻辑
    case HeartBeat(id) =>{
      //更新最后一次心跳时间
      registMap(id).lastHeartBeatTime = System.currentTimeMillis()
      //打印输出
      println(s"${id}心跳更新完成...")

    }
      //master端启动定时器
    case StartTimeOutWorker =>{
      //启动定时器
      import context.dispatcher //还需要import scala.concurrent.duration._
      context.system.scheduler.schedule(0 millis, 10000 millis, self, RemoveTimeOutWorker)
    }
    case RemoveTimeOutWorker =>{
      //定时清理超时6s的worker,scala
      //获取当前的时间
      val currentTime = System.currentTimeMillis()
      val workersInfo = registMap.values //获取到所有注册的worker信息
      //先将超时的一次性过滤出来，然后对过滤到的集合一次性删除
      workersInfo.filter( //进行过滤
        currentTime - _.lastHeartBeatTime > 6000
      ).foreach(workerInfo=>{
        registMap.remove(workerInfo .id)
      })

      printf("当前有%d个worker存活\n", registMap.size)
    }
  }
}

//程序入口
object SparkMaster extends App {
  val masterHost = "127.0.0.1"
  val masterPort = 10000

  //设置配置
  val config: Config = ConfigFactory.parseString(
    s"""
akka.actor.provider="akka.remote.RemoteActorRefProvider"
akka.remote.netty.tcp.hostname=$masterHost
akka.remote.netty.tcp.port=$masterPort
     """.stripMargin)

  //获取actorsystem
  val sparkmasterFactory = ActorSystem("SparkMaster", config)//第一个参数：指定actorSystem的名称，第二个参数：指定配置
  //获取创建sparkMaster和引用
  val sparkMasterRef: ActorRef = sparkmasterFactory.actorOf(Props[SparkMaster], "SparkMaster01")//第一个参数：使用给定的名称作为上下文鉴定
  //发送消息
  sparkMasterRef ! "start" //sendmessage 发送消息

}
