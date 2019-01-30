package SparkMasterWorker.worker

import java.util.UUID

import SparkMasterWorker.common.{HeartBeat, RegisterWorkerInfo, RegisteredWorkerInfo, SendHeartBeat}
import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._ //用于引入mills 时间计时单位的包

/*
第二个功能：定时发送心跳请求：
首先，自己发送给自己一个心跳请求
然后，自己发送给服务器端一个心跳请求
 */
//第一步:继承Actor
class SparkWorker(masterHost: String, masterPort: Int) extends Actor { //通过主构造器将主机和端口号进行初始化
  //第三步：需要持有对方的引用--spark master
  var masterProxy: ActorSelection = _
  var id = UUID.randomUUID().toString //产生一个随机的UUID

  //第四步：在preStart()中对master的代理有一个初始化
  override def preStart(): Unit = {
    masterProxy = context.actorSelection(s"akka.tcp://SparkMaster@${masterHost}:${masterPort}/user/SparkMaster01") //传入一个路径
  }

  //第二部：实现receive方法
  override def receive: Receive = {
    case "start" => {
      println("spark worker启动...")
      //启动后，发送注册的请求
      masterProxy ! RegisterWorkerInfo(id, 8, 8 * 1024) //进行注册，发送RegisterWorkerInfo--id随机产生，只是模拟
    }
    //发送注册请求后，服务端会回发一个成功消息，在这里进行接收
    case RegisteredWorkerInfo => {
      println(s"收到回复，${id}已成功注册...")

      import context.dispatcher //需要导入一个包
      //注册成功后，进行发送3s心跳请求
      context.system.scheduler.schedule(0 millis,3000 millis,self,SendHeartBeat)
    }
      //真正发送请求的地方
    case SendHeartBeat =>{
      println(s"workerid= $id 发出心跳~")
      masterProxy ! HeartBeat(id)
    }

  }
}

object SparkWorker extends App {
  val (masterHost, masterPort, workerHost, workerPort) =
    ("127.0.0.1", 10000, "127.0.0.1", 10001)

  //创建config
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider="akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$workerHost
       |akka.remote.netty.tcp.port=$workerPort
       """.stripMargin)
  //创建actorsystem
  val sparkWorker = ActorSystem("sparkWorker",config)
  //创建actor
  val sparkWorkerActorRef: ActorRef = sparkWorker.actorOf(Props(new SparkWorker(masterHost,masterPort)),"SparkMaster01")
  //通过ref 进行发送消息
  sparkWorkerActorRef ! "start"

}
