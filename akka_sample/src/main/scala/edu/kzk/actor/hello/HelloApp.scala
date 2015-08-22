package edu.kzk.actor.hello

import java.util.concurrent.TimeUnit
import scala.util.Success
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.util.Timeout
import akka.pattern._
import akka.actor.ActorSystemImpl
import scala.concurrent.duration.Duration
import com.oracle.jrockit.jfr.DurationEvent
import scala.concurrent.duration.`package`.DurationLong

object HelloApp extends App {

  // Create an actor system
  val actorSystem = ActorSystem("hello");

  // Create an actor
  val actor = actorSystem.actorOf(Props[SampleActor]);

  // Tell asynchronously
  actor ! 1;

  // Ask asynchronously
  implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS); // ?で必要
  val f = actor ? 2;

  import actorSystem.dispatcher

  f.onComplete {
    case Success(msg) => println(msg)
  }

  actorSystem.shutdown
  //actorSystem.awaitTermination(Duration(5000, TimeUnit.MILLISECONDS))

}
