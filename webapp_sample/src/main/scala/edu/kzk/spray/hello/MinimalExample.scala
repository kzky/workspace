package edu.kzk.spray.hello

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

object MinimalExample extends App with SimpleRoutingApp {

  implicit val system = ActorSystem("my-system")

  startServer(interface = "localhost", 8080) {
    path("hello") {
      get {
        complete {
          <h1>Say hello to spray</h1>
        }
      }
    }
  }
}