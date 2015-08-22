package edu.kzk.reflection

object RuntimeRelfectionApp extends App {
  /*
   * TypeTag
   */
  // Import runtime universe
  import scala.reflect.runtime.{ universe => ru }

  val l = List(1, 2, 3)

  // Context bound for ru.TypeTag
  def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]

  // Get type info
  val theType = getTypeTag(l).tpe

  // Inspection for declarations and members
  theType.decls
  theType.members

  // Get constructor method symbol
  println(theType.decl(ru.termNames.CONSTRUCTOR))

  /*
   * Reification with runtime.universe
   */

  // Reification of runtime type
  case class Person(name: String)

  // Get mirror
  var m = ru.runtimeMirror(getClass().getClassLoader())

  // Get Person class symbol
  val classPerson = ru.typeOf[Person].typeSymbol.asClass
  //val classPerson = m.staticClass("edu.kzk.reflection.RuntimeRelfectionApp.Person")

  // Get class mirror
  val cm = m.reflectClass(classPerson)

  // Get constructor method symbol
  val ctor = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod

  // Get constructor
  val ctorm = cm.reflectConstructor(ctor);

  // Call constructor
  val person = ctorm("Mike")
  println(person)
  println()

  /*
   * Instance member access
   */
  case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)
  val purchase = Purchase("Jeff Lebowski", 23819, false);

  // Get mirror
  m = ru.runtimeMirror(getClass().getClassLoader())

  // Get term symbol (have to newTermName)
  val shippingTermSymb = ru.typeOf[Purchase].decl(ru.TermName("shipped")).asTerm

  // Get instance mirror
  val im = m.reflect(purchase)

  // Get field mirror
  val shippingFieldMirror = im.reflectField(shippingTermSymb);

  // Get field
  println(shippingFieldMirror.get)

  // Get field
  shippingFieldMirror.set(true)
  println(shippingFieldMirror.get)

  /*
   * Reification using classTag
   */
  import scala.reflect._
  val ct = classTag[String]
  val x = ct.runtimeClass.newInstance() + "This is relfected instance";
  println(x);

}