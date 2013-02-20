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
		project.addWarning("just a stupid warning");

		return project;
	}

	@Override
	public void init() {
		CamPartTestDataFactory f = new CamPartTestDataFactory();
		parts.add(f.createDrilling10FrontsideCamPart());
		parts.add(f.createDrillingsBackSideAndHorizontalAndProfileCamPart());
		parts.add(f.createDrilling10And35CamPart());
		parts.add(f.createBigPart());
		parts.add(f.createProfileBottom());
		parts.add(f.createProfileLeft());
		parts.add(f.createProfileRight());
		parts.add(f.createProfileTop());
		parts.add(f.createDiagonalDrillingPart());
	}

	public void setCamPart(ImosPart camPart) {
		parts.clear();
		parts.add(camPart);
	}

}
