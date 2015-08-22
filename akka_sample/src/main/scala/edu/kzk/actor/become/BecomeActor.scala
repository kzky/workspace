package edu.kzk.actor.become

import akka.actor.Actor
import akka.actor.actorRef2Scala
import scala.concurrent.duration.Duration

class BecomeActor extends Actor {
  import context._

  def angry: Receive = {
    case "angry" => sender() ! "I am already angry"
    case "happy" => become(happy)
  }

  def happy: Receive = {
    case "happy" => sender() ! "I am already happy"
    case "angry" => become(angry)
  }

  def receive = {
    case "angry" => {
      println("original angry")
      become(angry);
    }
    case "happy" => {
      println("original happy")
      become(happy);
    }
  }
}
