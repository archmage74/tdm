package tdm.cam.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class TextFileReader {
	
	// private static final String TEMPLATE_PATH = "./templates/";
	
	public String readTemplate(String templateName) {
		String template = readResource("/" + templateName);
		return template.replace('\\', '/');
	}
	
	public String readResource(String name) {
		InputStream ris = this.getClass().getResourceAsStream(name);
		if (ris == null) {
			throw new RuntimeException("unable to read resource'" + name + "'");
		}
		
		String content = null;
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(ris);
			content = readFromReader(reader);
		} catch (IOException e) {
			throw new RuntimeException("unable to read resource'" + name + "'", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					// nothing to do here
				}
			}
		}
		return content;
	}
	
	public String readFile(String path) {
		String content = null;
		FileReader fr = null;
		try {
			fr = new FileReader(path);
			content = readFromReader(fr);
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

	private String readFromReader(Reader reader) throws IOException {
		StringBuffer content = new StringBuffer();
		char[] buffer = new char[10000];
		int cnt;
		while ((cnt = reader.read(buffer)) != -1) {
			content.append(new String(buffer, 0, cnt));
		}
		return content.toString();
	}
	
}
