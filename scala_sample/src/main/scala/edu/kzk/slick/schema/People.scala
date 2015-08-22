package edu.kzk.slick.schema

import scala.slick.driver.MySQLDriver.simple._

object People {
  type Person = (Int, String, Int, Int)
}

class People(tag: Tag)(implicit addresses: TableQuery[Addresses]) extends Table[People.Person](tag, "People") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def age = column[Int]("AGE")
  def addressId = column[Int]("ADDRESS_ID")
  def * = (id, name, age, addressId)

  def address = foreignKey("ADDRESS", addressId, addresses)(_.id)
}

