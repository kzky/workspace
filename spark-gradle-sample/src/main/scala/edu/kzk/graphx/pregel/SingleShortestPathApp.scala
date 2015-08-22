package edu.kzk.graphx.pregel

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.graphx.Graph

import org.apache.spark.graphx._

object SingleShortestPathApp {
    def main(args: Array[String]) {

        // Spark context
        val conf = new SparkConf();
        conf.setAppName("Single Shortest Path App");
        conf.setMaster("local");
        val sc = new SparkContext(conf);

        // A graph with edge attributes containing distances
        val graph: Graph[Long, Double] =
                GraphGenerators
                .logNormalGraph(sc, numVertices = 100)
                .mapEdges(e => e.attr.toDouble);
        
        val sourceId: VertexId = 42; // The ultimate source

        // Initialize the graph such that all vertices except the root have distance infinity.
        val initialGraph = graph.mapVertices((id, _) => if (id == sourceId) 0.0 else Double.PositiveInfinity);
        val sssp = initialGraph.pregel(Double.PositiveInfinity)(
                (id, dist, newDist) => math.min(dist, newDist), // Vertex Program
                triplet => {  // Send Message
                    if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
                        Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
                    } else {
                        Iterator.empty
                    }
                },
                (a,b) => math.min(a,b) // Merge Message
                );
        println(sssp.vertices.collect.mkString("\n"))
    }
}