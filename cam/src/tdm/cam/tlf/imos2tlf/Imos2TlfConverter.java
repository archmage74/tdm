package tdm.cam.tlf.imos2tlf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tdm.cam.imos.ImosDrilling;
import tdm.cam.imos.ImosPart;
import tdm.cam.imos.ImosProfile;
import tdm.cam.tlf.Drilling;
import tdm.cam.tlf.TlfPart;
import tdm.cam.tlf.TlfProfile;

public class Imos2TlfConverter {

	private TlfPartFactory partFactory;
	private TlfDrillingFactory drillingFactory;
	private TlfProfileFactory profileFactory;
	
	public Imos2TlfConverter() {
		partFactory = new TlfPartFactory();
		drillingFactory = new TlfDrillingFactory();
		drillingFactory.init();
		profileFactory = new TlfProfileFactory();
	}

	public TlfPart convert(ImosPart imosPart) {
		TlfPart tlfPart = partFactory.createTlfPart(imosPart);
		
		for (ImosDrilling imosDrilling : imosPart.getDrillings()) {
			Drilling tldDrilling = drillingFactory.createDrilling(tlfPart, imosDrilling);
			tlfPart.addDrilling(tldDrilling);
		}

		for (ImosProfile imosProfile : imosPart.getProfiles()) {
			TlfProfile tlfFrontSideProfile = profileFactory.createProfile(imosProfile);
			tlfPart.getFrontSide().addNode(tlfFrontSideProfile);
			TlfProfile tlfBackSideProfile = profileFactory.createProfile(imosProfile);
			tlfPart.getBackSide().addNode(tlfBackSideProfile);
		}

		tlfPart.optimizeSides();
		return tlfPart;
	}
	
	public Collection<TlfPart> convert(Collection<ImosPart> imosParts) {
		List<TlfPart> tlfParts = new ArrayList<TlfPart>();
		
		for (ImosPart imosPart : imosParts) {
			tlfParts.add(convert(imosPart));
		}
		
		return tlfParts;
	}
	
}
