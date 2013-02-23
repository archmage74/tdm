package tdm.bser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Mod {

	public static void main(String[] args) {
		try {
			new Mod().execute(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void execute(String[] args) throws Exception {
		byte[] inBuf = readFile(args[0]);
		byte[] outBuf = modify(inBuf);
		writeFile(args[1], outBuf);
	}

	private byte[] modify(byte[] inBuf) {
		byte[] pattern = new byte[] { 0x02, 0x59 };
		byte[] replace = new byte[] { 0x03, (byte) 0x80, 0x5b };

		ArrayList<byte[]> out = new ArrayList<byte[]>();
		int lastStart = 0;
		for (int i = 0; i < inBuf.length; i++) {
			if (doesMatch(pattern, inBuf, i)) {
				System.out.println("replace at 0x" + String.format("%05X", i));
				out.add(Arrays.copyOfRange(inBuf, lastStart, i));
				out.add(replace);
				lastStart = i + pattern.length;
				i += pattern.length;
			}
		}
		return join(out);
	}

	private byte[] join(ArrayList<byte[]> out) {
		int len = 0;
		for (byte[] buf : out) {
			len += buf.length;
		}
		byte[] result = new byte[len];

		int index = 0;
		for (byte[] buf : out) {
			for (int i = 0; i < buf.length; i++) {
				result[index++] = buf[i];
			}
		}
		return result;
	}

	private boolean doesMatch(byte[] pattern, byte[] inBuf, int index) {
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] != inBuf[index + i]) {
				return false;
			}
		}
		return true;
	}

	private void writeFile(String filename, byte[] outBuf) throws IOException {
		File f = new File(filename);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(outBuf);
		fos.close();
	}

	private byte[] readFile(String filename) throws IOException {
		File f = new File(filename);
		long len = f.length();
		byte[] b = new byte[(int) len];
		FileInputStream fis = new FileInputStream(f);
		fis.read(b);
		fis.close();
		return b;
	}

}
