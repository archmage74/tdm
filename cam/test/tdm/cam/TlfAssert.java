package tdm.cam;

import tdm.cam.util.TextFileReader;
import junit.framework.Assert;


public class TlfAssert extends Assert {

	public static void assertFileEquals(String expectedFile, String actualFile) {
		assertFileEquals(null, expectedFile, actualFile);
	}
	
	public static void assertFileEquals(String message, String expectedFile, String actualFile) {
		if (message == null) {
			message = "";
		}
		message += "; Files did not match: " + expectedFile + " - " + actualFile;
		TextFileReader tfr = new TextFileReader();
		String expected = tfr.readFile(expectedFile);
		String actual = tfr.readFile(actualFile);
		assertEquals(message, expected, actual);
	}
}
