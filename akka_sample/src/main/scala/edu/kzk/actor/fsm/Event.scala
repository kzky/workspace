package edu.kzk.actor.fsm

import akka.actor.ActorRef
import scala.collection.immutable.Seq

// received events
case class SetTarget(ref: ActorRef)
case class Queue(obj: Any)
case object Flush

// sent events
case class Batch(obj: Seq[Any])

