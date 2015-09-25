package edu.kzk.akka.cluster.worker_dialin

/**
 * @author kzk
 */

//#messages
case class TransformationJob(text: String)
case class TransformationResult(text: String)
case class JobFailed(reason: String, job: TransformationJob)
case object BackendRegistration
//#messages
