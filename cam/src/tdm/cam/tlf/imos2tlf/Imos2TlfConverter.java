package tdm.cam.tlf.imos2tlf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tdm.cam.export.transform.TransformationService;
import tdm.cam.model.imos.ImosDrilling;
import tdm.cam.model.imos.ImosPart;
import tdm.cam.model.imos.ImosProfile;
import tdm.cam.model.imos.ImosProject;
import tdm.cam.tlf.TlfDrilling;
import tdm.cam.tlf.TlfPart;
import tdm.cam.tlf.TlfProfile;

public class Imos2TlfConverter {

	private TlfPartFactory partFactory;
	private TlfDrillingFactory drillingFactory;
	private TlfProfileFactory profileFactory;
	private TransformationService transformationService; 
	
	public Imos2TlfConverter() {
		this.partFactory = new TlfPartFactory();
		this.drillingFactory = new TlfDrillingFactory();
		this.drillingFactory.init();
		this.profileFactory = new TlfProfileFactory();
		this.transformationService = new TransformationService();
	}

	public TlfPart convert(ImosPart imosPart) {
		TlfPart tlfPart = partFactory.createTlfPart(imosPart);
		
		for (ImosDrilling imosDrilling : imosPart.getDrillings()) {
			TlfDrilling tldDrilling = drillingFactory.createDrilling(tlfPart, imosDrilling);
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
		return convert(imosParts, new HashMap<String, Integer>());
	}
		
	public Collection<TlfPart> convert(Collection<ImosPart> imosParts, Map<String, Integer> rotationMap) {
		transformationService.rotate(imosParts, rotationMap);

		List<TlfPart> tlfParts = new ArrayList<TlfPart>();
		
		for (ImosPart imosPart : imosParts) {
			tlfParts.add(convert(imosPart));
		}
		
		return tlfParts;
	}

	public Collection<TlfPart> convert(ImosProject imosProject, Map<String, Integer> rotationMap) {
		return convert(imosProject.getParts(), rotationMap);
	}
}
