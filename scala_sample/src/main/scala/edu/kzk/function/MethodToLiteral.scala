package edu.kzk.function

class A(ver: String) {

  def addDoubly(a: Int, b: Int): Int = { return (a + b) * 2 };
  def addTriply(a: Int, b: Int): Int = { return (a + b) * 3 };

  var add = {
    ver match {
      case "ver1" => addDoubly _;
      case "ver2" => addTriply _;
      case _ => (a: Int, b: Int) => { a + b }: Int
    }
  }
}

object MethodToLiteral extends App {
  val a = new A("ver1");
  println(a.add(10, 20));

  val b = new A("ver2");
  println(b.add(10, 20));
}