package edu.kzk.actor.typedactor

trait HasName {
  def name(): String
}

class Named extends HasName {
  import scala.util.Random
  private val id = Random.nextInt(1024)

  def name(): String = "name-" + id
}
