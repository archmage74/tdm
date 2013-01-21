package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.imos.db.IImosService;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProject;


public class ImosServiceMock implements IImosService {

	private List<ImosPart> parts = new ArrayList<ImosPart>();
	
	@Override
	public ImosProject readProject(String orderId) {
		ImosProject project = new ImosProject();
		project.setOrderId(orderId);
		project.setName("mock-project");
		project.setParts(parts);
		
		return project;
	}

	@Override
	public void init() {
		CamPartTestDataFactory f = new CamPartTestDataFactory();
		parts.add(f.createDrillingsBackSideAndHorizontalAndProfileCamPart());
		parts.add(f.createDrilling10And35CamPart());
		parts.add(f.createBigPart());
	}
	
	public void setCamPart(ImosPart camPart) {
		parts.clear();
		parts.add(camPart);
	}

}
