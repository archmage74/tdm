package tdm.i2p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import tdm.i2p.db.imos.IMOSProperties;
import tdm.i2p.db.protime.ProtimeProperties;
import tdm.i2p.gui.MainView;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startGUI();
//		new Main().testConnection();
	}

	private static void startGUI() {
		Display display = new Display();

		MainView mainView = new MainView(display);
		Shell shell = mainView.show();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	@SuppressWarnings("unused")
	private void testConnection() {
		testProtime();
		testImos();
	}

	private void testImos() {
		Connection connection;
		IMOSProperties imos = new IMOSProperties();

		connection = null;

		try {
			Class.forName(imos.getDriverClass());
			connection = DriverManager.getConnection(imos.getUrl(), imos.getUserName(), imos.getPassword());
//			connection = DriverManager.getConnection(imos.getUrl());
		} catch (Exception e) {
			System.out.println("Unable to load the driver class!");
			e.printStackTrace();
		}

		System.out.println("imos db-access erfolgreich");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void testProtime() {
		ProtimeProperties protime = new ProtimeProperties();

		Connection connection = null;

		try {
			Class.forName(protime.getDriverClass());
			connection = DriverManager.getConnection(protime.getUrl(), protime
					.getUserName(), protime.getPassword());
		} catch (Exception e) {
			System.out.println("Unable to load the driver class!");
			e.printStackTrace();
		}

		System.out.println("protime db-access erfolgreich");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
