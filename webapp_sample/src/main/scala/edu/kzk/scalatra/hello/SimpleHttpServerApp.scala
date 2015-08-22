package edu.kzk.scalatra.hello

import akka.actor.{ Props, ActorRef, ActorSystem }
import akka.io.IO
import org.eclipse.jetty.servlet.DefaultServlet
import org.scalatra.servlet.ScalatraListener
import org.eclipse.jetty.webapp.WebAppContext
import org.eclipse.jetty.server.Server
import com.typesafe.scalalogging.slf4j.LazyLogging

object SimpleHttpServerAppwith extends App with LazyLogging {
  logger.info("start http server")

  // param
  val port = if (System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080
  val server = new Server(port)
  val context = new WebAppContext()

  // configure context
  context setContextPath "/"
  context.setResourceBase("src/main/webapp")
  context.setInitParameter(ScalatraListener.LifeCycleKey, "edu.kzk.scalatra.hello.ScalatraBootstrap")
  context.addEventListener(new ScalatraListener)
  context.addServlet(classOf[DefaultServlet], "/")

  // set context
  server.setHandler(context)

  server.start
  logger.info("server is running.")
  server.join
}