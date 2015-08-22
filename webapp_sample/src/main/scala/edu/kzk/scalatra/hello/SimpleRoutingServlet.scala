package edu.kzk.scalatra.hello

import org.scalatra.ScalatraServlet
import com.typesafe.scalalogging.slf4j.LazyLogging

class SimpleRoutingServlet extends ScalatraServlet with LazyLogging {

  var data: Int = _;

  get("/articles/:id") {
    val id = params("id")
    //println(s"${id} was obtained.")
    s"${id} was obtained."
  }

  post("/articles") {
    //println("Articles was posted.")
    "Articles was posted."
  }

  put("/articles/:id") {
    val id = params("id")
    //println("${id} was updated.")
    s"${id} was updated."
  }

  delete("/articles/:id") {
    val id = params("id")
    //println("${id} was deleted.")
    s"${id} was deleted."
  }

  get("/data") {
    //logger.info("data")
    this.data.toString()
  }

  def this(data: Int) = {
    this()
    this.data = data
  }
}