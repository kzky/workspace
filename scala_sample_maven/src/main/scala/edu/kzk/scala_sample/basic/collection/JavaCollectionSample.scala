package edu.kzk.scala_sample.basic.collection

import java.util.ArrayList
import scala.util.Random
import scala.collection.immutable.Range

object JavaCollectionSample {

    def main(args: Array[String]) = {
        // 要素の追加
        var list = new ArrayList[Int]();
        Random.setSeed(10L);
        var stime = System.currentTimeMillis();
        for(a <- Range(0, 30000)) {
            list.add(Random.nextInt(10)); 
            if (a % 10000 == 0)
                println(a);
        }
        println(list.size);
        var etime = System.currentTimeMillis();
        printf("total time: %d [s]\n", (etime - stime)/1000);
        
        for(i <- Range(0, 10)) {
            println(list.get(i));
        }
    }

}