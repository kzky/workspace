package edu.kzk.collection

object ClosureApp {
    def main(args: Array[String]) {

        val a = 2;
        
        var range = 0 to 100; // val a = range = 0 to 100; // also ok. 
        range.foreach(i => println(i * a));
        
    }
}