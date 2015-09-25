package edu.kzk.akka.cluster.group_router

/**
 * @author kzk
 */


//#messages
case class StatsJob(text: String)
case class StatsResult(meanWordLength: Double)
case class JobFailed(reason: String)
//#messages
