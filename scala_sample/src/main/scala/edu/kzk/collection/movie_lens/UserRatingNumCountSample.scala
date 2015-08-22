package edu.kzk.scala_sample.basic.collection.movie_lens

import scala.io.Source

object UserRatingNumCountSample {

    def main(args: Array[String]) = {
        // MapReduce [u] -> Map(u, [u]) -> Map(u, count([u]))
        // line -> (k, v) -> (k, [v]) -> (k, f(v))ではない．  
        var userKeyValList = List[Int]();
        var s = Source.fromFile("/home/kzk/datasets/movie_lens/ml-10M100K/ratings.dat", "UTF-8");
        println("### Start reading ###")
        for(line <- s.getLines) {
            var sl = line.split("::", -1);
            userKeyValList +:= (sl(0).toInt);
        }
        println("### Finish reading ###")

        // map reduce List[uid] -> {uid, [uid]} -> (uid, count)
        var stime = System.currentTimeMillis();
        var userRatingCount = userKeyValList.groupBy(key => key).map(kv => (kv._1, kv._2.size));
        var etime = System.currentTimeMillis();
        println(userRatingCount.take(100));
        printf("total time: %d [s]\n", (etime - stime)/1000);
        
    }
}