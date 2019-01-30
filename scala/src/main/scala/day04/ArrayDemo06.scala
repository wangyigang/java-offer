package day04

import java.util

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object ArrayDemo06 {
  def main(args: Array[String]): Unit = {
    val arrScala = ArrayBuffer("a","b","c")
    val listJava:util.List[String]= arrScala.asJava
    println(listJava)
    //javaList 转为scala List 需要调用asScala
    var buf:mutable.Buffer[String] = listJava.asScala
    println(buf.mkString(","))
  }

//  def main(args: Array[String]): Unit = {
//    val arrScala = ArrayBuffer("a","b","c")
//    val listJava: util.List[String] = arrScala.asJava
//    println(listJava)
//  }
}
