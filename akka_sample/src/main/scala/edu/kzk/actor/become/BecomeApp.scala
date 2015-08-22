package edu.kzk.actor.become

import scala.util.Success
import java.util.concurrent.TimeUnit
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration.Duration

object BecomeApp extends App {

  // Create an actor system
  val actorSystem = ActorSystem("become");

  // Create an actor
  val actor = actorSystem.actorOf(Props[BecomeActor]);

  // Ask asynchronously
  implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS); // ?で必要

  import actorSystem.dispatcher

  val f1 = actor ! "angry";
  Thread.sleep(1000);
  val f2 = actor ? "angry";
  Thread.sleep(1000);
  val f3 = actor ! "happy";
  Thread.sleep(1000);
  val f4 = actor ? "happy";
  Thread.sleep(1000);
  val f5 = actor ! "angry";
  Thread.sleep(1000);
  val f6 = actor ? "angry";

  f2.onComplete {
    case Success(msg) => println(msg)
  }

  f4.onComplete {
    case Success(msg) => println(msg)
  }

  f6.onComplete {
    case Success(msg) => println(msg)
  }

  actorSystem.shutdown
  //actorSystem.awaitTermination(Duration(5000, TimeUnit.MILLISECONDS))
}
