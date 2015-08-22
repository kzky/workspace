package edu.kzk.scalatra.hello

import org.scalatra._
import javax.servlet.ServletContext
import scala.util.Random

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {

    // init data
    val r = new Random
    val data = r.nextInt(10);
    context.mount(new SimpleRoutingServlet(data), "/simple")
  }
}

