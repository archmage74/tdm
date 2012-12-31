package tdm.cam.util;

import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {

	public String readTemplate(String path) {
		String template = readFile(path);
		return template.replace('\\', '/');
	}
	
	public String readFile(String path) {
		StringBuffer content = new StringBuffer();
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			char[] buffer = new char[10000];
			int cnt;
			while ((cnt = fr.read(buffer)) != -1) {
				content.append(new String(buffer, 0, cnt));
			}
		} catch (IOException e) {
			throw new RuntimeException("unable to read text file '" + path + "'", e);
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e1) {
					// nothing to do here
				}
			}
		}
		return content.toString();
	}
	
}
