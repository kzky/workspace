#!/usr/bin/groovy

println "'+' concatenate"
String str = "";
long stime = System.currentTimeMillis();
for(int i in 0..<100000) {
	"abc" + "def"
}
long etime = System.currentTimeMillis();
println "${etime - stime} ms"

println "'cancat' concatenate"
stime = System.currentTimeMillis();
for(int i in 0..<100000) {
	"abc".concat("def");
}
etime = System.currentTimeMillis();
println "${etime - stime} ms"




