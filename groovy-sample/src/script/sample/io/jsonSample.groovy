import java.nio.file.attribute.AclEntry.Builder;

import groovy.json.JsonBuilder;
import groovy.json.JsonSlurper


// json サンプル


// json slurper
def slurper = new JsonSlurper()
def fp = new File("/home/kzk/workspace/groovy-sample/src/main/resources/test.json").newReader()
def root = slurper.parse(fp)
//def fp = new File("/home/kzk/workspace/groovy-sample/src/main/resources/test.json").text
//def root = slurper.parseText(text)

root.each {
	println it
}

// json builder
def builder = new JsonBuilder()
map = [:]
map["langs"] = []
map["langs"].push(["languages":"japanese"])
map["langs"].push(["languages":"engilsh"])
map["langs"].push(["languages":"spanish"])
builder(map)
println builder.toString()



