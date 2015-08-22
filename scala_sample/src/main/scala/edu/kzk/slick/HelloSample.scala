package edu.kzk.slick

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery
import edu.kzk.slick.schema.Addresses
import edu.kzk.slick.schema.People

object HelloSample extends App {

  val scheme = "jdbc:mysql"
  val host = "localhost"
  val port = "3306"
  val db = "slick_sample"
  val url = s"${scheme}://${host}:${port}/${db}"
  val driver = "com.mysql.jdbc.Driver"
  val user = "mysql"
  val password = "harumaki51"

  Database.forURL(url, driver = driver,
    user = user,
    password = password) withSession {

      implicit session =>

        println("Read sample")
        implicit lazy val addresses = TableQuery[Addresses];
        addresses.foreach {
          case (id, street, city) =>
            println(s"id=${id}, street=${street}, city=${city}")
        }

        println("Join sample")
        val people = TableQuery[People];
        val joinQuery = for {
          p <- people
          a <- p.address
        } yield (p.name, p.age, a.street, a.city)
        joinQuery.foreach {
          case (name, age, street, city) =>
            println(s"name=${name}, age=${age}, street=${street}, city=${city}")
        }

    }
}