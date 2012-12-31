package tdm.i2p.db.imos;

import tdm.i2p.db.DBProperties;

public class IMOSProperties implements DBProperties {
	
	public IMOSProperties() {
		
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
