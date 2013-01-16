package tdm.cam.tlf.imos2tlf;

import tdm.cam.imos.ImosPart;
import tdm.cam.tlf.TlfPart;

public class TlfPartFactory {

	public TlfPart createTlfPart(ImosPart imosPart) {
		TlfPart tlfPart = new TlfPart();
		
		tlfPart.setDimensions(imosPart.getDimensions());
		tlfPart.setName(imosPart.getName());
		tlfPart.setBarcode(imosPart.getBarcode());
		
		return tlfPart;
	}
	
}
