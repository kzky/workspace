package edu.kzk.actor.hello

import akka.actor.Actor
import akka.actor.actorRef2Scala

class SampleActor extends Actor {
  def receive = {
    case i: Int => {
      println(s"Message ${i} is received.");
      sender() ! "Reply Mesage!";
    }
  }
}

