package edu.kzk.actor.router

import akka.actor.Actor
import akka.pattern._

class Worker extends Actor {
  def receive = {
    case x: String => sender ! x
  }
}