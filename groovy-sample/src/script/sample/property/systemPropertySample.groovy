
// VMの環境変数
println "--- VM ---"
p = System.getProperties()
p.each {k, v ->
	println k + ": " + v
}

// システムの環境変数
println "--- system ---"
p = System.getenv()
p.each {k, v ->
	println k + ": " + v
}

// -D指定のプロパティ
println ""
println "--- '-D' spesified property ---"
println System.getProperty("this_script")

