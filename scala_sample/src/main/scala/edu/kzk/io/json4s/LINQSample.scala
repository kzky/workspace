package edu.kzk.io.json4s

import org.json4s._
import org.json4s.jackson.JsonMethods._

object LINQSample extends App {
  // json value
  val jvalue = parse("""
         { "name": "joe",
           "age": 33,
           "children": [
             {
               "name": "Mary",
               "age": 5
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)

  println(pretty(render(jvalue)))

  val value = jvalue.values;
  println(value)

  // LINQ-style
  val queryResults = for {
    JObject(child) <- jvalue
    JField("age", JInt(age)) <- child
  } yield age // return as list

  println(queryResults)

}
