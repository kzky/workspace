package edu.kzk.slick

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.StaticQuery.interpolation
import scala.slick.jdbc.GetResult
import scala.slick.lifted.TableQuery

import edu.kzk.slick.schema.Addresses
import edu.kzk.slick.schema.People

object SQLSample extends App {
  val scheme = "jdbc:mysql"
  val host = "localhost"
  val port = "3306"
  val dbName = "slick_sample"
  val url = s"${scheme}://${host}:${port}/${dbName}"
  val driver = "com.mysql.jdbc.Driver"
  val user = "mysql"
  val password = "harumaki51"

  val db = Database.forURL(url, driver = driver,
    user = user,
    password = password)
  val query = sql"select id, name, age from People".as[(Int, String, Int)]

  val people = db.withSession {
    implicit session =>
      query.list.foreach(p =>
        println(s"id=${p._1}, name=${p._2}, age=${p._3}")
      )
  }

}