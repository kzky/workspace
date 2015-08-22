package edu.kzk.actor.mailbox.control.message

import akka.event.LoggingAdapter
import akka.event.Logging
import akka.actor.Actor

class ControlMessageActor extends Actor {
  // We create a new Actor that just prints out what it processes

  val log: LoggingAdapter = Logging(context.system, this);

  def receive = {
    case x => log.info(x.toString)
  }

}
