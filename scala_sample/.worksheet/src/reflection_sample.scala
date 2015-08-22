

object reflection_sample {
	/*
	*
	* TypeTag
	*
	*/
	// Import runtime universe
	import scala.reflect.runtime.{universe => ru};import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(154); 
		
	val l = List(1, 2, 3);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(90); 
	
	// Context bound for ru.TypeTag
	def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T];System.out.println("""getTypeTag: [T](obj: T)(implicit evidence$2: reflect.runtime.universe.TypeTag[T])reflect.runtime.universe.TypeTag[T]""");$skip(54); 
 
  // Get type info
	val theType = getTypeTag(l).tpe;System.out.println("""theType  : reflect.runtime.universe.Type = """ + $show(theType ));$skip(36); val res$0 = 
	
	// Inspect
	theType.declarations
	
	/*
	*
	* Reification
	*
	*/
	
	// Reification of runtime type
	case class Person(name: String);System.out.println("""res0: reflect.runtime.universe.MemberScope = """ + $show(res$0));$skip(170); 
	
	// Get mirror
	val m = ru.runtimeMirror(getClass().getClassLoader());System.out.println("""m  : reflect.runtime.universe.Mirror = """ + $show(m ))}
	
	// Get Person class
	//val classPerson = ru.typeOf[Person].typeSymbol.asClass
	
	// Get class mirror
	//val cm = m.reflectClass(classPerson)
	
	// Get constructor symbol
	//val ctor = ru.typeOf[Person].declaration(ru.nme.CONSTRUCTOR).asMethod
	
	
	//
	
	
}
