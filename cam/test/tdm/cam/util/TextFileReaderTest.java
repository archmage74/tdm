package tdm.cam.util;

import junit.framework.Assert;

import org.junit.Test;

public class TextFileReaderTest {

	@Test
	public void readFileTest() {
		String newline = System.getProperty("line.separator");
		String expected = "this is a test content" + newline + "with a line break";
		TextFileReader testee = new TextFileReader();
		String actual = testee.readFile("test/tdm/cam/util/textFileReaderTest.txt");
		Assert.assertEquals(expected, actual);
	}
	
}
