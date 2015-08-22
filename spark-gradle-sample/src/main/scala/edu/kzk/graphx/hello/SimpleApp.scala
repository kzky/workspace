package edu.kzk.graphx.hello

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx.impl.VertexRDDImpl


object SimpleApp {
    def main(args: Array[String]) {

        /*
         * Graph Construction
         */

        // spark context
        val conf = new SparkConf();
        conf.setAppName("Simple Test Application");
        conf.setMaster("local")
        val sc = new SparkContext(conf);
      
        // Create an RDD for the vertices
        val users: RDD[(VertexId, (String, String))] =
                sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
                        (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))

        // Create an RDD for edges
        val relationships: RDD[Edge[String]] =
                sc.parallelize(Array(Edge(3L, 7L, "collab"),    Edge(5L, 3L, "advisor"),
                        Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))
                        
        // Define a default user in case there are relationship with missing user
        val defaultUser = ("John Doe", "Missing")
        
        // Build the initial Graph
        val graph = Graph(users, relationships, defaultUser)

        
        /*
         * Vertices and Edge Views 
         */
        // Count all users which are postdocs
        val countPostdocs = graph.vertices.filter { case (id, (name, pos)) => pos == "postdoc" }.count
        println(s"# of postdoc is user ${countPostdocs}");

        // Count all the edges where srcId > dstId
        val countIdRelation = graph.edges.filter(e => e.srcId > e.dstId).count
        println(s"# of edges which srcId = dstId is ${countIdRelation}");
        
        /*
         * Triplet View
         */
        // Use the triplets view to create an RDD of facts.
        val facts: RDD[String] = graph.triplets.map(
                triplet =>
                triplet.srcAttr._1 + " is the " + triplet.attr + " of " + triplet.dstAttr._1)
        facts.collect.foreach(println(_))        
        

    }
}