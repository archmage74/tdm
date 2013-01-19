package tdm.cam.tlf;

import tdm.cam.imos.db.IImosService;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;


public class ImosServiceMock implements IImosService {

	private ImosPart camPart;
	
	@Override
	public ImosProject readProject(String orderId) {
		ImosProject project = new ImosProject();
		project.setOrderId(orderId);
		project.setName("mock-project");
		project.addPart(camPart);
		
		return project;
	}

	@Override
	public void init() {
		CamPartTestDataFactory f = new CamPartTestDataFactory();
		camPart = f.createDrillingsBackSideAndHorizontalAndProfileCamPart();
	}
	
	public void setCamPart(ImosPart camPart) {
		this.camPart = camPart;
	}

}
