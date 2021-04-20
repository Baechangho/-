package xml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class genSnippet {
	public static void main(String[] args) {
		String query = "";
		System.out.println(args);
		query = args[5];
		
		Path path = Paths.get("input.txt");
		Charset cs = StandardCharsets.UTF_8;
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		try {
			list = Files.readAllLines(path,cs);
		}catch(IOException e) {
			System.out.println();
		}for(String readLine : list) {
			System.out.println(readLine);
			sb.append(readLine+"\n");
		}
		System.out.println(sb.toString());
	}
}
