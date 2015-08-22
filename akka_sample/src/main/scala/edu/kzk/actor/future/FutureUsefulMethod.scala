package edu.kzk.actor.future

import scala.concurrent.Future
import scala.concurrent.duration._
import akka.pattern._
import scala.concurrent.impl.Future
import scala.concurrent.Future

object FutureUsefulMethod extends App {

  import scala.concurrent.ExecutionContext;
  import ExecutionContext.Implicits.global

  val futureList = Future.traverse((1 to 100).toList)(x => Future(x * 2 - 1))
  val oddSum = futureList.map(_.sum)
  oddSum foreach println

  val futureList1 = Future.sequence((1 to 100).toList.map(x => Future(x * 2 - 1)))
  val oddSum1 = futureList1.map(_.sum)
  oddSum1 foreach println

  // Create a sequence of Futures
  val futures = for (i <- 1 to 1000) yield Future(i * 2)
  val futureSum = Future.fold(futures)(0)(_ + _)
  futureSum foreach println

  // Create a sequence of Futures
  val futures1 = for (i <- 1 to 1000) yield Future(i * 2)
  val futureSum1 = Future.fold(futures1)(0)(_ + _)
  futureSum1 foreach println

}