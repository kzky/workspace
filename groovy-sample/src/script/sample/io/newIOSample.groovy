import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.File

import javax.swing.text.WhitespaceBasedBreakIterator;

println "--- path ---"
fs = FileSystems.getDefault()
path = fs.getPath("/home/kzk/.zshrc");
println "default FS: " + fs
println "path: " + path

println "--- raedlines ---"
list = Files.readAllLines(path, Charset.forName("UTF-8")) 
for(String line in list){
	println line 
}

println "--- readline ---"
bf = Files.newBufferedReader(path, Charset.forName("UTF-8"))
while((line = bf.readLine()) != null){
	println line
}
bf.close();

println "--- file/dir list ---"
files = new File("/home/kzk").listFiles()
files.each {
	println it
}







