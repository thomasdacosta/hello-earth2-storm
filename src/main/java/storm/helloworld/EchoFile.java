package storm.helloworld;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class EchoFile {
	
	public static void file(String fileName, String content) {
		Path path = FileSystems.getDefault().getPath(".", fileName);
		try {
			Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}

}
