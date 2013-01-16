package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.List;

import tdm.cam.imos.ImosPart;
import tdm.cam.imos.db.IImosService;


public class ImosServiceMock implements IImosService {

	private ImosPart camPart;
	
	@Override
	public List<ImosPart> readParts(String orderId) {
		List<ImosPart> parts = new ArrayList<ImosPart>();
		parts.add(camPart);
		return parts;
	}

	public void setCamPart(ImosPart camPart) {
		this.camPart = camPart;
	}

}
