package edu.kzk.scala_sample.basic.collection

import scala.util.Random


object CollectionSample {

    def main(args: Array[String]) {
        
        // 基本
        val l = List("a", "b", "c");
        val m = Map("a" -> 123, "b" -> 456, "c" -> 789);
        val s = Set("a", "b", "c");
        
        println("List", l);
        println("Map", m);
        println("Set", s);
        
        println("Map KeyValue");
        for(kv <- m) {
            println(kv._1, kv._2);
        }
        
        // 要素の追加
        var list = List[Int]();
        //var list = MutableList[Int](); // mutableだとaddした時に，newして返す()ので遅い
        //var list = LinkedList[Int](); // _.mutable.LinkedListは+:=が見つからなかったが遅い．
        //var list = ListBuffer[Int](); // ListBufferもaddでnewしているので遅い．
        Random.setSeed(10L);
        var stime = System.currentTimeMillis();
        for(a <- Range(0, 30000)) {
            // 先頭に追加していったほうが確実に早い
            //list = Random.nextInt(10) +: list; 
          list +:= Random.nextInt(10); 
            if (a % 10000 == 0)
                println(a);
        }
        println(list.size);
        var etime = System.currentTimeMillis();
        printf("total time: %d [s]\n", (etime - stime)/1000);
        println(list.take(100));
        

    }
}