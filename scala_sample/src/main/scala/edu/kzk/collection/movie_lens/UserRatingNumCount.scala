package edu.kzk.scala_sample.basic.collection.movie_lens

import scala.io.Source
import scala.collection.mutable.MutableList
import scala.collection.mutable.LinkedHashMap

/**
 * @author kzk
 *
 * Collection[T].mapの戻り値がCollection[T]なので，
 * MapReduceを行いたい場合は，新しくforeachをつかって，代入しないといけない．
 */
object UserRatingNumCount {

    def main(args: Array[String]) = {
        // line -> (k, v) -> (k, [v]) -> (k, f(v)) version
        val filename = "/home/kzk/datasets/movie_lens/ml-10M100K/ratings_head_1000000.dat";
        var dat = List[String]();
        var s = Source.fromFile(filename, "UTF-8");
        println("### Start reading ###")
        for(line <- s.getLines) {
            dat +:= line;
        }
        s.close
        println("### Finish reading ###")

        /*
         * map reduce List[uid] -> [(uid, 1)] -> {uid, [1]}-> (uid, count)
         */
        var stime = System.currentTimeMillis();
        // map
        val mapOutput = dat.map(line => {
            var sl = line.split("::", -1);
            Tuple2(sl(0).toInt, 1); // List[Tuple2] 元がリストなので．
        });
        
        println(mapOutput.take(10));
        
        // shuffle/sort
        var reduceInput = Map[Int, List[Int]](); 
        mapOutput.foreach(kv => {
            var v = reduceInput.get(kv._1);
            if (v == None) {
                reduceInput += (kv._1 -> List(kv._2));
            } else {
                reduceInput += (kv._1 -> (kv._2 :: v.get));
            }
        });

        println(reduceInput.take(10));
        
        // reduce
        var reduceOutput = Map[Int, Int]();
        reduceInput.foreach(kv => {
            val v = kv._2.sum;
            reduceOutput += (kv._1 -> v);
        })
        
        val userRatingCount = reduceOutput;
        
        println(userRatingCount.getClass());
        println(userRatingCount.take(100));
        
        var etime = System.currentTimeMillis();

        printf("total time: %d [s]\n", (etime - stime)/1000);

        
    }
}