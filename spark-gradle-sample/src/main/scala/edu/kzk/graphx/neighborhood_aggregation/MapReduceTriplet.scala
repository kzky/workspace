package edu.kzk.graphx.neighborhood_aggregation

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.graphx.VertexRDD
import org.apache.spark.graphx.Graph

object MapReduceTriplet {
    def main(args: Array[String]) {
        // spark context
        val conf = new SparkConf();
        conf.setAppName("Simple Test Application");
        conf.setMaster("local")
        val sc = new SparkContext(conf);


        // Graph auto-generation
        val graph: Graph[Double, Int] =
                GraphGenerators.logNormalGraph(sc, numVertices = 100).mapVertices( (id, _) => id.toDouble );
        
        // Compute the number of older followers and their total age
        val olderFollowers: VertexRDD[(Int, Double)] = 
                graph.mapReduceTriplets[(Int, Double)](
                        triplet => { // Map Function
                            if (triplet.srcAttr > triplet.dstAttr) {
                                // Send message to destination vertex containing counter and age
                                Iterator((triplet.dstId, (1, triplet.srcAttr)))
                            } else {
                                // Don't send a message for this triplet
                                Iterator.empty
                            }
                        },

                        // Add counter (= the number of follower) and age
                        (a, b) => (a._1 + b._1, a._2 + b._2) // Reduce Function
                        );


        // Divide total age by number of older followers to get average age of older followers
        val avgAgeOfOlderFollowers: VertexRDD[Double] =
                olderFollowers.mapValues( (id, value) => value match { case (count, totalAge) => totalAge / count } );

        // Display the results
        avgAgeOfOlderFollowers.collect.foreach(println(_))

    }
}