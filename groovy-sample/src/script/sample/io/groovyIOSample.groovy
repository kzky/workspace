file = new File("/home/kzk")

println "--- each file and directory ---"
file.eachFile {
	println it
}
println "-- each directory---"
file.eachDir {
	println it
}

file = new File("/home/kzk/.zshrc")
file.eachLine {line ->
	println line
} 

println "--- each obj ---"
x = [10, 5, 6]
x.each {
	println it
}

y = ["x":0, "y":1, "z":10]
y.each {k, v ->
	println k + " -> " + v
}

