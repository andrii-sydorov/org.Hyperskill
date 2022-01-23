package WritingFiles;

import java.nio.charset.Charset;
import java.util.SortedMap;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

public class MakeCharset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Charset ch = Charset.forName("UTF-16");
		ch.aliases().forEach(x -> System.out.println(x));
		File f = new File("./src/WritingFiles/data.txt");

		try (PrintWriter fw = new PrintWriter(f); FileReader fr = new FileReader(f);) {
			fw.printf("%s dolor sit %s", "Lorem", "ipsum", "amet");
			System.out.println(fr.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		SortedMap <String, Charset> st = Charset.availableCharsets();
		//st.forEach((key, value) -> System.out.println(key + " " + value));
		for (Charset c : st.values()) {
			System.out.println(c);
		}
	}

}
