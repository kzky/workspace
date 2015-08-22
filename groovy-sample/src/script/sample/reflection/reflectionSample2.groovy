import java.lang.reflect.Modifier;

// reflectoin sample 2

public class Test {
	public x = 10;
	public y = 5
	protected z = 1
	private str = "String"
	
	public getStr() {
		return str
	}
	public setStr(String str) {
		this.str = str 
	}
}

println "--- class generation ---"
clazz = Class.forName("edu.kzk.groovy_sample.reflection.Test")
test = clazz.newInstance()
println test

println "--- method invocation ---"
setMethod = clazz.getMethod("setStr", String.class)
setMethod.invoke(test, "invoked!")
getMethod = clazz.getMethod("getStr", null)
println getMethod.invoke(test, null)

println "--- get public fields ---"
fields = clazz.getFields()
for (f in fields) {
	println Modifier.toString(f.getModifiers())

	println f.getName() + ": " + f.get(test)
}
println "get non-public field"
field = clazz.getDeclaredField("z")
field.setAccessible(true)
println field.getName() + ": " + field.get(test)



