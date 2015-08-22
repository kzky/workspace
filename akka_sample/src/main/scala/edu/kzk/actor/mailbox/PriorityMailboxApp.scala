package edu.kzk.actor.mailbox

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.PoisonPill

object PriorityMailboxApp extends App {

  implicit val system = ActorSystem("priority-mailbox-system");
  val actor = system.actorOf(Props[ActorWithPriorityMailbox].withMailbox("prio-mailbox"));

  actor ! 'lowpriority
  actor ! 'lowpriority
  actor ! 'highpriority
  actor ! 'pigdog
  actor ! 'pigdog2
  actor ! 'pigdog3
  actor ! 'highpriority
  actor ! PoisonPill

  system.shutdown()
}