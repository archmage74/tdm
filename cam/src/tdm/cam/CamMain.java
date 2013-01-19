package tdm.cam;

import tdm.cam.export.Exporter;
import tdm.cam.imos.db.IImosService;
import tdm.cam.imos.db.ImosServiceFactory;

public class CamMain {
	
	private static final String ORDER_ID = "hbohr";
	
	public static final String EXPORT_PATH = "./tlf";
	
	public static void main(String[] args) {
		CamMain main = new CamMain();
		main.execute();
	}
	
	private void execute() {
		Exporter exporter = new Exporter();
		exporter.setImosService(createImosService());
		exporter.setExportPath(EXPORT_PATH);
		exporter.export(ORDER_ID);
	}
	
	private IImosService createImosService() {
		ImosServiceFactory imosServiceFactory = ImosServiceFactory.getInstance();
		IImosService imosService = imosServiceFactory.getImosService();
		return imosService;
	}
	
}
