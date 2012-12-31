package tdm.cam.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TextFileWriter {

	public void printEscapedTlf(String escapedTlf, PrintStream ps) {
		String tlf = escapedTlf.replace('/', '\\');
		ps.print(tlf);
	}
	
	public void writeEscapedTlf(String escapedTlf, String path) {
		PrintStream ps = null;
		try {
			ps = new PrintStream(path);
			printEscapedTlf(escapedTlf, ps);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Could not write to file '" + path + "'", e);
		} finally {
			if (ps != null) {
				ps.close();
				ps = null;
			}
		}
	}
	
}
