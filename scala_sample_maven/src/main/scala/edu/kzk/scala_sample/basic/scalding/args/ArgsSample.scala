package edu.kzk.scala_sample.basic.scalding.args

import com.twitter.scalding.Args

object ArgsSample {

  def main(args: Array[String]) = {
      val argsList = "--input /home/kzk/datasets/movie_lens/ml-10M100K/ratings_head_100000.dat --output /home/kzk/tmp/args_sample.out";
      val a = Args(argsList);
      println(a("input"));
      println(a("output"));
  }

}