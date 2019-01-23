import akka.actor.{Actor, ActorSystem, Props}


class SayHelloActor extends Actor {
  //实现receive方法--receive是一个偏函数
  override def receive: Receive = {
    case "start" => println("actor开始运行...")
    case "hello"=>println("hello too:)")
    case "fish"=> println("<・)))><< 鱼")
    case "cat" => println("(>^ω^<)喵..")
      //让actor停止
    case "exit"=> {
      println("准备退出!~")
      context.stop(self)// 停止当前的actor
      context.system.terminate()
    }
  }
}


object SayHelloActorDemo {
  def main(args: Array[String]): Unit = {
    //1创建一个ActorSystem
    val actorFactory = ActorSystem("actorFactory")
    //2.通过actorFactory创建需要的actor

    //说明
    //1. "SayHelloActor" 这个是actor的名字，有程序员指定.
    //2. Props[SayHelloActor] 是使用反射机制创建了 SayHelloActor 的实例
    //3. sayHelloActorRef ： 是创建的 SayHelloActor 的引用, 代理(proxy)
    val sayHelloActorRef = actorFactory.actorOf(Props[SayHelloActor],"SayHelloActor")

//    sayHelloActorRef ! "start"
//    sayHelloActorRef ! "hello"
//    sayHelloActorRef ! "fish"
//    sayHelloActorRef ! "cat"
//    sayHelloActorRef ! "exit"
    sayHelloActorRef ! "start"
  }
}
