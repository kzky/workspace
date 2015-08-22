package edu.kzk.actor.typedactor

import akka.routing.RoundRobinGroup
import akka.actor.TypedProps
import akka.actor.ActorRef
import akka.actor.TypedActor
import akka.actor.ActorSystem

object TypedRouter extends App {

  implicit val system = ActorSystem("Typed-Router")

  // creation method of typed actor
  def namedActor(): HasName = TypedActor(system).typedActorOf(TypedProps[Named]())

  // prepare routees
  val routees: List[HasName] = List.fill(5) { namedActor() }
  val routeePaths = routees map { r =>
    TypedActor(system).getActorRefFor(r).path.toStringWithoutAddress
  }

  println(routeePaths)

  // prepare untyped router
  val router: ActorRef = system.actorOf(RoundRobinGroup(routeePaths).props())

  // prepare typed proxy, forwarding MethodCall messages to `router`
  val typedRouter: HasName =
    TypedActor(system).typedActorOf(TypedProps[Named](), actorRef = router)

  println("actor was: " + typedRouter.name())
  println("actor was: " + typedRouter.name())
  println("actor was: " + typedRouter.name())
  println("actor was: " + typedRouter.name())

  system.shutdown()
}