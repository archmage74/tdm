package tdm.bser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Bin1 {
	
	public static void main(String[] args) {
		try {
			new Bin1().execute(args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void execute(String[] args) throws Exception {
		Obj1 o1 = createObj1(args);
		writeObj1(args[0], o1);
	}
	

	private void writeObj1(String filename, Obj1 o) throws FileNotFoundException, IOException {
		File f = new File(filename);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(o);
		oos.flush();
		oos.close();
	}

	private Obj1 createObj1(String[] args) {
		int val = Integer.parseInt(args[1]);
		Obj1 o = new Obj1();
		o.test = val;
		return o;
	}
}
