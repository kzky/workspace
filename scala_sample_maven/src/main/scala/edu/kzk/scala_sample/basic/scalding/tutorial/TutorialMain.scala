package edu.kzk.scala_sample.basic.scalding.tutorial

import com.twitter.scalding.Args
import com.twitter.scalding.Job
import com.twitter.scalding.TextLine
import com.twitter.scalding.Tsv

/**
 * どうもeclipseから実行できなそうな感じ．
 * 
 * @author kzk
 *
 */
object TutorialMain {

    def main(args: Array[String]) = {
        println("main start");
        new Tutorial01(Args(""));
        //      new Tutorial02(Args(""));
        //      new Tutorial03(Args(""));
        
        println("main finish");
    }
}

class Tutorial01(args : Args) extends Job(args) {
    // constructor
    //val input = TextLine("/home/kzk/datasets/movie_lens/ml-10M100K/ratings_head_100000.dat");
    val input = TextLine("/home/kzk/documents/scala/data/hello.txt");
    val output = TextLine("/home/kzk/tmp/scalding_tutorial_01.txt");
    input.read.write(output);
    //input.flatMap('line -> 'word){line: String => {println(line);line}};

}

class Tutorial02(args : Args) extends Job(args) {
    // constructor
    val input = TextLine("/home/kzk/datasets/movie_lens/ml-10M100K/ratings_head_100000.dat");
    val output = TextLine("/home/kzk/tmp/scalding_tutorial_02.txt");
    input.read.project('line).write(output);  
}
class Tutorial03(args : Args) extends Job(args) {

    val input = TextLine("/home/kzk/datasets/movie_lens/ml-10M100K/ratings_head_100000.dat");
    val output = TextLine("/home/kzk/tmp/scalding_tutorial_03.txt")

            input
            .read
            .flatMap('line -> 'word){ line : String => {
                println(line)
                line.split("::")
            }
    }
    .write(Tsv("/home/kzk/tmp/scalding_tutorial_03.tsv"))
    .project('word)
    .write(output)
}
