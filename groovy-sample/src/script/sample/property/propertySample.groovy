
clazz = this.getClass()
is = clazz.getResourceAsStream("test.properties");
p = new Properties()
p.load(is)
is.close()

println "--- java style ---"
println p.getProperty("airwave")
for (kv in p.entrySet()) {
	println kv.getKey() + ": " + kv.getValue()	
}

println "--- groovy style ---"
println p["airwave"]
p.each {k, v ->
	println k + ": " + v
}

println p.stringPropertyNames()

