object method_to_literal_func {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 
  println("Welcome to the Scala worksheet")
 
 	import java.lang.String
 	
  class A {
 		var b = (a: Int, b: Int) => {a}: Int;
 		def add(a: Int, b: Int): Int = {return a + b};
 		
  };$skip(164); 
  
  val a = new A();System.out.println("""a  : method_to_literal_func.A = """ + $show(a ));$skip(16); ;
  a.b = a.add _;$skip(23); 
  println(a.b(10, 20))}
 
 	 	
  
}
