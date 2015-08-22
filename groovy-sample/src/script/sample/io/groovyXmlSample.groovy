import groovy.xml.MarkupBuilder

// xml builder/parser/slurper sample

// builder
// StringWriterを作ってXmlBuilderに渡す．
def sw = new StringWriter()
def builder = new MarkupBuilder(sw)
builder.lang(type:"current", count:3, mainstream:true){
	language(lang:"java", flavor:"static", version:"1.7") {
		dialect("scalar", flavor:"dynamic", version:"1.0")
		dialect("groovy", flavor:"dynamic", version:"1.8")
	}
	language("java", flavor:"static", version:"1.9")
	language("javascript", flavor:"dynamic", version:"1.9")
}
// println builder.toString() <- だめ 
println "--- xml builder ---"
println sw

// parser
def xml = '''
<langs type='current' count='3' mainstream='true'>
  <language flavor='static' version='1.5'>Java</language>
  <language flavor='dynamic' version='1.6.0'>Groovy</language>
  <language flavor='dynamic' version='1.9'>JavaScript</language>
</langs>
'''
def langs = new XmlParser().parseText(xml)
println "--- xml parser ---"
println langs.getClass()
println langs
langs.attributes().each {k, v ->
	println k + ": " + v
}

// *. and gpath
println "--- expand operator + gpath ---" 
println langs.language*.attributes()
println langs.language*.attribute("version")
println langs.language*.text()

// slurper
langs = new XmlSlurper().parseText(xml)
println "--- slurper ---"
println langs.@count // .@で直感的なアクセス
langs.language.each {
	println it.@flavor
}
println langs.language*.text() // '.@' and '?.' operatorも使用可能


