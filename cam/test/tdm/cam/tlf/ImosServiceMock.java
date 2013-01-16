package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.imos.db.IImosService;


public class ImosServiceMock implements IImosService {

	private TlfPart camPart;
	
	@Override
	public List<TlfPart> readParts(String orderId) {
		List<TlfPart> parts = new ArrayList<TlfPart>();
		parts.add(camPart);
		return parts;
	}

	public void setCamPart(TlfPart camPart) {
		this.camPart = camPart;
	}

}
