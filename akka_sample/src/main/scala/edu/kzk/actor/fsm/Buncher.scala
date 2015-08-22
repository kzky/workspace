package edu.kzk.actor.fsm

import akka.actor.FSM
import akka.actor.FSM.Event
import scala.concurrent.duration._

class Buncher extends FSM[State, Data] {

  startWith(Idle, Uninitialized)

  when(Idle) {
    case Event(SetTarget(ref), Uninitialized) =>
      stay using Todo(ref, Vector.empty)
  }

  // transition elided ...
  when(Active, stateTimeout = 1 second) {
    case Event(Flush | StateTimeout, t: Todo) =>
      goto(Idle) using t.copy(queue = Vector.empty)
  }

  // unhandled elided ...
  whenUnhandled {
    // common code for both states
    case Event(Queue(obj), t @ Todo(_, v)) =>
      goto(Active) using t.copy(queue = v :+ obj)

    case Event(e, s) =>
      log.warning("received unhandled request {} in state {}/{}", e, stateName, s)
      stay
  }

  // transition
  onTransition {
    case Active -> Idle =>
      stateData match {
        //stateData; nextStateData; Both old state data and new state data can be accessed to
        // Batch(queue) is sent to caller as an action
        case Todo(ref, queue) => ref ! Batch(queue);
      }
  }

  initialize()
}

