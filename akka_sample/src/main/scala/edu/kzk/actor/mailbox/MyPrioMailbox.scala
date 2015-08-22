package edu.kzk.actor.mailbox

import akka.dispatch.PriorityGenerator
import akka.dispatch.UnboundedPriorityMailbox
import com.typesafe.config.Config
import akka.actor.ActorSystem
import akka.actor.PoisonPill

// We inherit, in this case, from UnboundedPriorityMailbox
// and seed it with the priority generator
class MyPrioMailbox(settings: ActorSystem.Settings, config: Config)
    extends UnboundedPriorityMailbox(
      // Create a new PriorityGenerator, lower prio means more important
      PriorityGenerator {
        // ’highpriority messages should be treated first if possible
        case 'highpriority => 0
        // ’lowpriority messages should be treated last if possible
        case 'lowpriority => 2
        // PoisonPill when no other left

        case PoisonPill => 3
        // We default to 1, which is in between high and low
        case otherwise => 1
      }) {

}
