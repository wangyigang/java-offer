package yellowchickenServer.common

/*
客户端发送的消息体
 */
case class ClientMessage(msg:String)
//服务端发送到的消息体
case class ServerMessage(msg:String)
