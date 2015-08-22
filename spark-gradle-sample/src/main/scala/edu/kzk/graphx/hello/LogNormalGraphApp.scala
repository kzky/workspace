package edu.kzk.graphx.hello

import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.graphx.Graph

object LogNormalGraphApp {
    def main(args: Array[String]) {
        // Spark context
        val conf = new SparkConf();
        conf.setAppName("Single Shortest Path App");
        conf.setMaster("local");
        val sc = new SparkContext(conf);

        // A graph with edge attributes containing distances
        val graph: Graph[Long, Int] =
                GraphGenerators
                .logNormalGraph(sc, numVertices = 100);
                
        // show vertices and edges of log normal graph
        println("----- Vertices -----");
        graph.vertices.foreach(println(_));
        
        println("----- Edges -----");
        graph.edges.foreach(println(_));

    }
}