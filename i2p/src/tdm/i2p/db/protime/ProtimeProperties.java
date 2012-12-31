package tdm.i2p.db.protime;

import tdm.i2p.db.DBProperties;

public class ProtimeProperties implements DBProperties {
	
	public ProtimeProperties() {
		
	}

	@Override
	public String getDriverClass() {
		return "jdbc.gupta.sqlbase.SqlbaseDriver";
	}

	@Override
	public String getPassword() {
		return "";
	}

	@Override
	public String getUrl() {
		return "jdbc:sqlbase://server:2155/ptdb";
	}

	@Override
	public String getUserName() {
		return "sysadm";
	}
	
	

}
