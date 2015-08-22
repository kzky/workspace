package edu.kzk.actor.typedactor

import akka.actor.TypedProps
import akka.actor.TypedActor
import akka.actor.ActorSystem
import scala.concurrent.Future
import akka.actor.SupervisorStrategy
import akka.actor.SupervisorStrategyConfigurator
import akka.actor.TypedActor.Supervisor

object TypedActorSampleApp extends App {

  implicit val system = ActorSystem("Typed-Actor-Sample")
  import system.dispatcher

  // Constructor
  val mySquarer: Squarer =
    TypedActor(system).typedActorOf(TypedProps[SquarerImpl]())

  // Constructor with argument
  val otherSquarer: Squarer =
    TypedActor(system).typedActorOf(TypedProps(classOf[Squarer],
      new SquarerImpl("foo")), "name")

  val f: Future[Int] = mySquarer.square(3);
  f.onComplete { x => println(x.get) }

  val i = mySquarer.squareNow(4);
  println(i)

  val j = mySquarer.squareNowPlease(5)
  println(j.get)

  //  TypedActor(system).poisonPill(mySquarer)
  //  TypedActor(system).poisonPill(otherSquarer)

  system.shutdown()

}