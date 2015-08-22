package edu.kzk.spark.hello

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext.rddToPairRDDFunctions

object SimpleApp {
    def main(args: Array[String]) {

        // spark context
        val conf = new SparkConf();
        conf.setAppName("Simple Test Application");

        // when local application
        //conf.setMaster("local[3]");

        // when an application on cluster
        conf.setMaster("yarn-client");
        //conf.setMaster("yarn-cluster");
        val sc = new SparkContext(conf);

        // wordcount
        //val filepath = "file:///home/kzk/datasets/news20/news20.dat"; // when on local
        val filepath = "datasets/news20/news20.dat"; // when on cluster
        val data = sc.textFile(filepath, 2).cache();

        val result = data.flatMap(line => line.split(" "))
                .map(elm => (elm.split(":")(0), 1))
                .reduceByKey(_ + _);

        result.take(10).foreach(elm => println(elm));

    }
}