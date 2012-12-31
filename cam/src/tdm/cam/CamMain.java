package tdm.cam;

import java.sql.Connection;
import java.sql.SQLException;

import tdm.cam.db.ConnectionProvider;
import tdm.cam.db.imos.IImosService;
import tdm.cam.db.imos.ImosProperties;
import tdm.cam.db.imos.ImosService;
import tdm.cam.export.Exporter;

public class CamMain {
	
	public static final String EXPORT_PATH = "./tlf";
	
	public static void main(String[] args) {
		CamMain main = new CamMain();
		main.execute();
	}
	
	private void execute() {
		Exporter exporter = new Exporter();
		exporter.setImosService(createImosService());
		exporter.setExportPath(EXPORT_PATH);
		exporter.export("test18");
	}
	
	private IImosService createImosService() {
		ImosService imosService = new ImosService();
		ImosProperties imosProps = new ImosProperties();
		Connection connection;
		try {
			connection = ConnectionProvider.getConnection(imosProps);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		imosService.setDbConnection(connection);
		return imosService;
	}
	
}
