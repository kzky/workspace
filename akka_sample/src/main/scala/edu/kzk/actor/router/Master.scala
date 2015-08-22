package edu.kzk.actor.router

import akka.actor.Actor
import akka.actor.Props
import akka.routing.RoundRobinPool
import akka.pattern._

class Master extends Actor {
  val nWorkers = 5;
  val workerRouterPool =
    context.actorOf(Props[Worker].withRouter(RoundRobinPool(nWorkers)), name = "workerRouter")

  def receive = {
    case x: String => workerRouterPool.forward(x)
  }

}