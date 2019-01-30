package yellowchickenServer.client

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import yellowchickenServer.common.{ClientMessage, ServerMessage}

import scala.io.StdIn
class CustomerActor extends Actor {
  //我们这里需要持有Server的Ref
  var yellowChickenServerRef: ActorSelection = _

  //preStart , 在启动Actor之前会先运行，因此变量,初始化写入preStart

  override def preStart(): Unit = {
    //println("preStart")
    //说明
    //1. 在AKKA 的Actor模型中， 认为 每个Actor都是一个资源（角色），通过一个Path来定位一个actor
    //2. path 的组成 akka.tcp://Server的actorfactory名字@ServerIp:Server的port/user/ServerActor名字
    yellowChickenServerRef = context.actorSelection("akka.tcp://Server@127.0.0.1:9999/user/YellowChickenServer")
  }

  override def receive: Receive = {
    case "start" => {
      println("客户端启动，可以咨询问题~~")
    }
    case mes: String => {
      //将mes 发送给Server
      yellowChickenServerRef ! ClientMessage(mes)
    }
    case ServerMessage(mes) => {
      println("收到客服回复的消息: " + mes)
    }

  }
}

object CustomerActor extends App {

  //编写必要的配置信息
  val serverHost = "127.0.0.1"
  val serverPort = 9999
  val clientHost = "127.0.0.1"
  val clientPort = 10000

  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider="akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$clientHost
       |akka.remote.netty.tcp.port=$clientPort
       """.stripMargin)

  //创建CustomerActor
  val clientActorSystem = ActorSystem("Client", config)

  val customerActorRef: ActorRef = clientActorSystem.actorOf(Props[CustomerActor], "CustomerActor")

  customerActorRef ! "start"

  println("可以咨询问题了")
  while (true) {
    val mes = StdIn.readLine()
    customerActorRef ! mes //先发给自己，然后让  CustomerActor 发

  }
}