

object reflection_sample {
	/*
	*
	* TypeTag
	*
	*/
	// Import runtime universe
	import scala.reflect.runtime.{universe => ru}
		
	val l = List(1, 2, 3)
	
	// Context bound for ru.TypeTag
	def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]
 
  // Get type info
	val theType = getTypeTag(l).tpe
	
	// Inspect
	theType.declarations
	
	/*
	*
	* Reification
	*
	*/
	
	// Reification of runtime type
	case class Person(name: String)
	
	// Get mirror
	val m = ru.runtimeMirror(getClass().getClassLoader())
	
	// Get Person class
	//val classPerson = ru.typeOf[Person].typeSymbol.asClass
	
	// Get class mirror
	//val cm = m.reflectClass(classPerson)
	
	// Get constructor symbol
	//val ctor = ru.typeOf[Person].declaration(ru.nme.CONSTRUCTOR).asMethod
	
	
	//
	
	
}