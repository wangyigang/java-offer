package yellowchickenServer.server

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import yellowchickenServer.common.{ClientMessage, ServerMessage}

class YellowChickenServer extends Actor{
  override def receive:Receive = {
    case "start" => {
      println("小妹开始监听程序，可以咨询问题~~")

    }
    case ClientMessage(mes) => {
      //怎么匹配他的内容
      println("客户咨询的问题是" + mes)
      mes match {
        case "姓名" => {
          sender() ! ServerMessage("wangyg")
        }
        case "地址" => {
          sender() ! ServerMessage("昌平区龙锦苑东一区")
        }
        case "工作" => {
          sender() ! ServerMessage("程序猿...")
        }
        case _ => {
          sender() ! ServerMessage("啥也不是~")
        }
      }
    }
  }
}
  object YellowChickenServer extends App{

    //创建ActorSystem
    //因为这时，我们需要监听网络，所以使用如下方法创建工厂
    //Config 就是我们的网络配置 ip , port..
    //def apply(name: String, config: Config): ActorSystem = apply(name, Option(config), None, None)

    val host = "127.0.0.1" //ip4
    val port = 9999
    //Config 就是我们的网络配置 ip , port..
    //
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$host
         |akka.remote.netty.tcp.port=$port
       """.stripMargin)

    val serverActorSystem = ActorSystem("Server",config)

    val yellowChickenServerRef: ActorRef = serverActorSystem.actorOf(Props[YellowChickenServer],"YellowChickenServer")

    //akka.tcp://Server@127.0.0.1:9999  就是Actor 路径
    yellowChickenServerRef ! "start"

  }