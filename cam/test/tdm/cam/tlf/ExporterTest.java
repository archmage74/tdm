package tdm.cam.tlf;

import org.junit.Before;
import org.junit.Test;

import tdm.cam.export.Exporter;
import tdm.cam.model.imos.ImosPart;

public class ExporterTest {

	CamPartTestDataFactory camPartFactory;
	
	@Before
	public void setup() {
		camPartFactory = new CamPartTestDataFactory();
	}
	
	@Test
	public void exportDrilling10and35Test() {
		ImosServiceMock imosService = new ImosServiceMock();
		ImosPart camPart = camPartFactory.createDrilling10And35CamPart();
		imosService.setCamPart(camPart);

		Exporter exporter = new Exporter();
		exporter.setImosService(imosService);
		exporter.setExportPath("./tlf/test/");
		
		exporter.export("testtuer");
	}

}
