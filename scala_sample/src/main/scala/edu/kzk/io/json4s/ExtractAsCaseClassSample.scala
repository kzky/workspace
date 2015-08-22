package edu.kzk.io.json4s

import org.json4s._
import org.json4s.jackson.JsonMethods._

object ExtractAsCaseClassSample extends App {
  implicit val formats = DefaultFormats // Brings in default date formats etc.

  // Declaration of case class
  case class Child(name: String, age: Int, birthdate: Option[java.util.Date])
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address, children: List[Child])

  // json value
  val jvalue = parse("""
         { "name": "joe",
           "address": {
             "street": "Bulevard",
             "city": "Helsinki"
           },
           "children": [
             {
               "name": "Mary",
               "age": 5,
               "birthdate": "2004-09-04T18:06:22Z"
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)

  val person = jvalue.extract[Person]
  println(person)

  //  val address = json.extract[Address]
  //  println(address)

}