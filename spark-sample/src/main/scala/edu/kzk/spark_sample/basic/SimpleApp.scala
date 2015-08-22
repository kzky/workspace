package edu.kzk.spark_sample.basic

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object SimpleApp {
    def main(args: Array[String]) {
        // from local
        val logFile = "/home/kzk/datasets/wordcount/word_count_input.txt";
        
        // 最後の2つはoption
        //val sc = new SparkContext("local", "Simple App", "YOUR_SPARK_HOME",
        //List("target/scala-2.10/simple-project_2.10-1.0.jar"))

        val sc = new SparkContext("local", "Simple App", 
            "/opt/cloudera/parcels/SPARK/");
        val logData = sc.textFile(logFile, 2).cache();
        val numAs = logData.filter(line => line.contains("a")).count();
        val numBs = logData.filter(line => line.contains("b")).count();
        println("Lines with a: %s, Lines with b: %s".format(numAs, numBs));
            
        // from hds
        val textFile = sc.textFile("hdfs://localhost:8020/user/kzk/sample/input/word_count_input.txt");
        val wordCounts = textFile.flatMap(l => l.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b);
        wordCounts.take(100).foreach(e => println(e));
            
    }
}
