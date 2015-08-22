package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Sample1 { 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath("./files/test.txt");

		Path path2 = Paths.get("./files/test2.txt");
		Path pathDir = Paths.get("./files");		

		try {
			Files.createFile(path);
			Files.createFile(path2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Files.walkFileTree(pathDir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					System.out.println(path);
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (BufferedWriter bf = 
				Files.newBufferedWriter(path, Charset.forName("utf-8"))) {
			bf.write("ff" + 10 + "千里");
			bf.write("faf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
