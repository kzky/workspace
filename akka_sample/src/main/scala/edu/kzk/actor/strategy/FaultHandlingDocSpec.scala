package edu.kzk.actor.strategy

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.WordSpecLike
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Terminated
import akka.actor.actorRef2Scala
import akka.testkit.DefaultTimeout
import akka.testkit.EventFilter
import akka.testkit.ImplicitSender
import akka.testkit.TestKit
import com.typesafe.config.ConfigFactory

@RunWith(classOf[JUnitRunner])
class FaultHandlingDocSpec extends TestKit(ActorSystem("TestKitUsageSpec",
  ConfigFactory.parseString("""
          akka.loggers = ["akka.testkit.TestEventListener"]
          """))) with DefaultTimeout with ImplicitSender
    with WordSpecLike with Matchers with BeforeAndAfterAll {
  //import FaultHandlingDocSpec._

  "A supervisor" must {

    "apply the chosen strategy for its child" in {

      val supervisor = system.actorOf(Props[Supervisor], "supervisor")

      supervisor ! Props[Child]
      val child = expectMsgType[ActorRef] // retrieve answer from TestKit’s testActor

      EventFilter.warning(occurrences = 1) intercept {
        println("EventFilter.warning(occurrences = 1)")
        child ! 42 // set state to 42
        child ! "get"
        println(expectMsg(42))

        child ! new ArithmeticException // crash it
        child ! "get"
        println(expectMsg(42))
      }

      EventFilter[NullPointerException](occurrences = 1) intercept {
        println(EventFilter[NullPointerException](occurrences = 1))
        child ! new NullPointerException // crash it harder
        child ! "get"
        println(expectMsg(0))
      }

      EventFilter[IllegalArgumentException](occurrences = 1) intercept {
        println(EventFilter[IllegalArgumentException](occurrences = 1))
        watch(child) // have testActor watch “child”
        child ! new IllegalArgumentException // break it
        expectMsgPF() { case Terminated(`child`) => () }
      }

      EventFilter[Exception]("CRASH", occurrences = 2) intercept {
        println(EventFilter[Exception]("CRASH", occurrences = 2))
        supervisor ! Props[Child] // create new child
        val child2 = expectMsgType[ActorRef]

        watch(child2)
        child2 ! "get" // verify it is alive
        println(expectMsg(0))

        child2 ! new Exception("CRASH") // escalate failure
        expectMsgPF() {
          case t @ Terminated(`child2`) if t.existenceConfirmed => ()
        }

        // As default policy, top-level actor restarts childs, which is not desired in some cases.
        // so as to override preStart in Supervisor2
        val supervisor2 = system.actorOf(Props[Supervisor2], "supervisor2")

        supervisor2 ! Props[Child]
        val child3 = expectMsgType[ActorRef]

        child3 ! 23
        child3 ! "get"
        println(expectMsg(23))

        child3 ! new Exception("CRASH")
        child3 ! "get"
        println(expectMsg(0))
      }

    }
  }
}