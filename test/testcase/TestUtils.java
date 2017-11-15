package testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestUtils {

	public static String readTestCode(String name) throws FileNotFoundException {
		File f = new File("test/testcase/"+name+".vrbs");
		Scanner s = new Scanner(new FileInputStream(f));
		String content = "";
		while(s.hasNext()) {
			content += s.nextLine() + "\n";
		}
		s.close();
		return content;
	}
	
}
