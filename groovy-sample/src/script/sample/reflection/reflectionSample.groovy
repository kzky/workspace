// reflection のサンプル
import java.lang.reflect.*

println "--- class generation ---"
Class<?> clazz = Class.forName("java.util.ArrayList")
loader = ClassLoader.getSystemClassLoader() // class loaderを使用して．
clazz2 = loader.loadClass("java.lang.StringBuilder")

List<String> list = (List<String>)clazz.newInstance() 
list.add("hoo")
list.add("foo")
list.add("hoge")
println list

str = clazz2.newInstance()
str.append("string")
str.append("builder!")

println "--- method invocation ---"
method = str.getClass().getMethod("append", String.class)
method.invoke(str, "method invocation")
println str.getClass().getMethod("toString", null).invoke(str, null)

println "--- method name/modifier ---" 
println method.getName()
println Modifier.toString(method.getModifiers())

 