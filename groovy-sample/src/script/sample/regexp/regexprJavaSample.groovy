import java.util.regex.Pattern
import java.util.regex.Matcher

println "Java version"
println "Matcher.match()"
pattern = Pattern.compile("(.*)/(.*)/(.+)")
matcher = pattern.matcher("hoge/foo/");
println matcher.matches();

println ""
println "Matcher.find()"
m = Pattern.compile("123").matcher("abc123def123ghi");
System.out.println(m.find());

println ""
println "Matcher.replace/Matcher.replaceAll"
m = Pattern.compile("123").matcher("abc123def123ghi");
System.out.println(m.replaceFirst("ZZZ"));
System.out.println(m.replaceAll("ZZZ"));

println ""
println "split()"
str = "chisato1,chisato2,chisato3,chisato4,\n";
println str.trim().split(",")
println str.trim().split(",", -1)




 



