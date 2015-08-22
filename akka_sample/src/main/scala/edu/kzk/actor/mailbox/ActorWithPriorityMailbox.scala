package edu.kzk.actor.mailbox

import akka.actor.Actor
import akka.event.LoggingAdapter
import akka.event.Logging

class ActorWithPriorityMailbox extends Actor {
  val log: LoggingAdapter = Logging(context.system, this);

  def receive = {
    case x => log.info(x.toString)
  }
}