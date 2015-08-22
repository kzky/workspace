package edu.kzk.actor.fsm

import akka.actor.Props
import scala.collection.immutable
import akka.testkit.TestKit
import akka.testkit.ImplicitSender
import org.scalatest.BeforeAndAfterAll
import akka.testkit.DefaultTimeout
import org.junit.runner.RunWith
import org.scalatest.Matchers
import akka.actor.FSM
import org.scalatest.WordSpecLike
import akka.actor.ActorSystem
import org.scalatest.junit.JUnitRunner
import scala.collection.immutable.Seq

import edu.kzk.actor.fsm.Buncher

@RunWith(classOf[JUnitRunner])
class FSMDocSpec extends TestKit(ActorSystem("TestKitUsageSpec")) with DefaultTimeout with ImplicitSender
    with WordSpecLike with Matchers with BeforeAndAfterAll {

  // fsm code elided ...

  "simple finite state machine" must {

    "demonstrate NullFunction" in {
      class A extends FSM[Int, Null] {
        val SomeState = 0
        when(SomeState)(FSM.NullFunction)
      }
    }

    "batch correctly" in {
      val buncher = system.actorOf(Props[Buncher])
      buncher ! SetTarget(testActor)
      buncher ! Queue(42)
      buncher ! Queue(43)
      println(expectMsg(Batch(Seq(42, 43))))
      buncher ! Queue(44)
      buncher ! Flush
      buncher ! Queue(45)
      println(expectMsg(Batch(Seq(44))))
      println(expectMsg(Batch(Seq(45))))
    }

    "not batch if uninitialized" in {
      val buncher = system.actorOf(Props[Buncher])
      buncher ! Queue(42)
      println(expectNoMsg)
    }
  }
}

