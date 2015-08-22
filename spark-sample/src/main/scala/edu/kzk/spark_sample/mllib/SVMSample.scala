package edu.kzk.spark_sample.mllib

import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint

object SVMSample {
    def main(args: Array[String]) {

        val sc = new SparkContext("local", "SVM sample",
                "/opt/cloudera/parcels/SPARK/");

        // Load and parse the data file
        val file = "/home/kzk/datasets/spark-sample/svm_data.txt";
        val data = sc.textFile(file);
        val parsedData = data.map { line =>
        val parts = line.split(' ')
        LabeledPoint(parts(0).toDouble, parts.tail.map(x => x.toDouble).toArray)
        }

        // Run training algorithm to build the model
        val numIterations = 20;
        val model = SVMWithSGD.train(parsedData, numIterations);

        // Evaluate model on training examples and compute training error
        val labelAndPreds = parsedData.map { point =>
        val prediction = model.predict(point.features)
        (point.label, prediction)
        }
        val trainErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / parsedData.count;
        println("Training Error = " + trainErr)      
    }
}