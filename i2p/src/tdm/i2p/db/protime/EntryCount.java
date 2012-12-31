package tdm.i2p.db.protime;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tdm.i2p.db.ConnectionProvider;

public class EntryCount {
	
	Connection connection;
	
	HashMap<String, ArrayList<Integer>> tables = new HashMap<String, ArrayList<Integer>>();
	
	ArrayList<String> recordNames = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		System.out.println("Database Table Entry Counter");
		
		EntryCount entryCount = new EntryCount();
		
		try {
			entryCount.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws SQLException, IOException {
		help();

		connection = ConnectionProvider.getConnection(new ProtimeProperties());
		DatabaseMetaData metaData = connection.getMetaData();
		
		getTableNames(metaData);
		String userEntry = "Initial";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		while (!"exit".equals(userEntry)) {
			if ("write".equals(userEntry)) {
				try {
					PrintStream fpos = new PrintStream(new File("changes.csv"));
					printDifferentEntries(fpos);
					fpos.close();
				} catch (IOException e) {
					System.out.println("Cannot write to file!");
				}
			} else if (userEntry== null || "".equals(userEntry) || "help".equals(userEntry)) {
				help();
			} else {
				getEntryCounts(userEntry);
				printDifferentEntries(System.out);
			}
			System.out.print("('exit' to end program) record name:");
			userEntry = reader.readLine();
		}

		PrintStream fpos = new PrintStream(new File("changes.csv"));
		printDifferentEntries(fpos);
		fpos.close();
	}

	private void getTableNames(DatabaseMetaData metaData) throws SQLException {
		String[] types = {"TABLE"}; 
		ResultSet rs = metaData.getTables(null, null, "%", types);
		
		while(rs.next()) {
			String tableName = rs.getString("TABLE_NAME");
			tables.put(tableName, new ArrayList<Integer>());
		}
	}

	public void getEntryCounts(String readKey) throws SQLException {
		PreparedStatement ps;
		recordNames.add(readKey);
		
		System.out.print("Reading table entries ");
		
		
		int cnt = 0;
		for (Map.Entry<String, ArrayList<Integer>> tableCount : tables.entrySet()) {
			cnt ++;
			if (cnt % 5 == 0) {
				System.out.print(".");
			}
			ps = connection.prepareStatement("SELECT count(*) FROM " + tableCount.getKey());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int columnCount = rs.getInt(1);
			tableCount.getValue().add(columnCount);
			rs.close();
			ps.close();
		}
		System.out.println();
	}
	
	public void printDifferentEntries(PrintStream out) {
		out.print("TABLES");
		for (String recordName : recordNames) {
			out.print(";" + recordName);
		}
		out.println();
		
		for (Map.Entry<String, ArrayList<Integer>> tableCount : tables.entrySet()) {
			if (isDifferent(tableCount.getValue())) {
				out.print(tableCount.getKey());
				for (int count : tableCount.getValue()) {
					out.print(";" + count);
				}
				out.println();
			}
		}
		out.println();
	}
	
	public boolean isDifferent(ArrayList<Integer> counts) {
		boolean same = true;

		if (counts == null || counts.size() == 0) {
			return same;
		}

		int init = counts.get(0);
		for (int value : counts) {
			same &= (init == value);
		}
		
		return !same;
	}
	
	public void help() {
		System.out.println("'exit'        - exits the program and writes the output to changes.csv");
		System.out.println("'write'       - writes the output to changes.csv");
		System.out.println("'' or 'help'  - prints this help");
		System.out.println("anything else - records current table entry counts with the given name");
		System.out.println();
	}
}

