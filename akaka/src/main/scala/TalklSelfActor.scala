import akka.actor.{Actor, ActorSystem, Props}

class TalklSelfActor extends Actor {
  //重写receive方法
  override def receive: Receive = {
    case "start" => println("actor 开始运行...")
    case "hello" => println("hello too...")
    case "fish" => println("<・)))><< 鱼")
    case "cat" => println("(>^ω^<)喵..")
    case "exit" => {
      println("正在停止...")
      context.stop(self) //停止self--self就是一个akka.actor.actorref
      context.system.terminate()
    }

  }
}

//object TalklSelfActorTest {
//  def main(args: Array[String]): Unit = {
//    val actorFactory = ActorSystem("actorFactory")
//    val talklSelfActor = actorFactory.actorOf(Props[TalklSelfActor], "TalklSelfActor")
//    talklSelfActor ! "start"
//    talklSelfActor ! "hello"
//    talklSelfActor ! "fish"
//    talklSelfActor ! "cat"
//    talklSelfActor ! "exit"
//  }
//}

object TalklSelfActorTest{
  def main(args: Array[String]): Unit = {
    //创建一个actorFactory--命名为actorFactory
    val actorFactory = ActorSystem("actorFactory")
    val talklSelfActor = actorFactory.actorOf(Props[TalklSelfActor],"TalklSelfActor")
    //! 符号调用了sendmessage方法  def !(message: Any)(implicit sender: ActorRef = Actor.noSender) = underlying.sendMessage(message, sender)
    talklSelfActor ! "start"

  }
}

//import akka.actor.{Actor, ActorSystem, Props}
//
///*
//1. 继承Actor
//2. 实现receive方法
// */
//class TalklSelfActor extends  Actor{
//  override def receive: Receive = {
//    case "start"=> println("actor 开始运行...")
//    case "hello"=> println("hello too...")
//    case "fish" =>  println("<・)))><< 鱼")
//    case "cat" => println("(>^ω^<)喵..")
//    case  "exit"=>{
//      println("准备退出...")
//      context.stop(self)  //self 就是一个akka.actor.actorRef--
//      context.system.terminate()//停止一切
//    }
//  }
//}
//
//object TalklSelfActorTest{
//  def main(args: Array[String]): Unit = {
//    //创建一个aActorsystem
//    val actorFactory = ActorSystem("actorFactory")
//    //通过actorFactory创建需要的actor
//    val talklSelfActor = actorFactory.actorOf(Props[TalklSelfActor], "TalklSelfActor")
//    talklSelfActor ! "start"
//    talklSelfActor ! "hello"
//    talklSelfActor ! "fish"
//    talklSelfActor ! "cat"
//    talklSelfActor ! "exit"
//  }
//}


