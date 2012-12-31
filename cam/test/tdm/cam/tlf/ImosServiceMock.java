package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.db.imos.IImosService;

public class ImosServiceMock implements IImosService {

	private CamPart camPart;
	
	@Override
	public List<CamPart> readParts(String orderId) {
		List<CamPart> parts = new ArrayList<CamPart>();
		parts.add(camPart);
		return parts;
	}

	public void setCamPart(CamPart camPart) {
		this.camPart = camPart;
	}

}
