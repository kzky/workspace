package edu.kzk.slick.schema

import scala.slick.driver.MySQLDriver.simple._

object Addresses {
  type Address = (Int, String, String)
}

class Addresses(tag: Tag) extends Table[Addresses.Address](tag, "Addresses") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def street = column[String]("STREET")
  def city = column[String]("CITY")
  def * = (id, street, city)
}