package tdm.cam.imos.db;

import tdm.cam.db.DBProperties;

public class ImosProperties implements DBProperties {
	
	public ImosProperties() {
		
	}

	@Override
	public String getDriverClass() {
		return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}

	@Override
	public String getPassword() {
		return "imos";
	}

	@Override
	public String getUrl() {
		return "jdbc:sqlserver://SERVER:2495;databaseName=IMOS;";
	}

	@Override
	public String getUserName() {
		return "IMOSADMIN";
	}
	
	

}
