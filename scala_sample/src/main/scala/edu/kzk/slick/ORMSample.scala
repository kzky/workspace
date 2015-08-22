package edu.kzk.slick

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

import edu.kzk.slick.schema.Addresses
import edu.kzk.slick.schema.People

object ORMSample extends App {

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

        implicit lazy val addresses = TableQuery[Addresses]
        val people = TableQuery[People];
        val peopleQuery: Query[People, People.Person, Seq] =
          people.filter { p => p.id === 2 }

        val peopleQueryResults = peopleQuery.run
        peopleQueryResults.foreach { p => println(s"name=${p._2}, age=${p._3}") }

        val addressesQuery: Query[Addresses, Addresses.Address, Seq] =
          peopleQuery.flatMap(_.address)
        val addressesQueryResults = addressesQuery.run
        addressesQueryResults.foreach {
          case (id, city, street) => s"id=${id}, city=${city}, street=${city}"
        }

    }
}