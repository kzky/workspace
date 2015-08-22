package edu.kzk.actor.router

import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern._
import scala.concurrent.Future
import akka.util.Timeout

object RouterApp extends App {

  implicit val system = ActorSystem("routing-actor-system");
  val master = system.actorOf(Props[Master])

  import system.dispatcher;
  implicit val timeout = Timeout(100000)

  val futures = for (i <- 0 to 100) yield {
    val future: Future[Any] = master ? s"message ${i}";
    future.onComplete { x => println(x.get) }
  }

  Thread.sleep(5000);

  system.shutdown()
}