package edu.kzk.actor.fsm

import akka.actor.ActorRef
import scala.collection.immutable.Seq

// states
sealed trait State
case object Idle extends State
case object Active extends State

sealed trait Data
case object Uninitialized extends Data
case class Todo(target: ActorRef, queue: Seq[Any]) extends Data
