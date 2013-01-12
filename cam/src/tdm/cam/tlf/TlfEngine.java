package tdm.cam.tlf;

import java.util.List;

import tdm.cam.export.NamedDecimalRenderer;
import tdm.cam.export.NamedIntRenderer;
import tdm.cam.util.TextFileReader;

public class TlfEngine {

	private TextFileReader textFileReader = new TextFileReader();
	
	private static boolean initialized = false;
	private static final String TEMPLATE_PATH = "./templates/";
	
	public TlfEngine() {
		
	}
	
	public List<TlfDocument> createTlfDocuments(CamPart camPart) {
		init();
		camPart.optimizeSides();
		TlfPart tlfPart = new TlfPart();
		return tlfPart.createTlfDocuments(camPart);
	}
	
	private void init() {
		if (TlfEngine.initialized) {
			return;
		}
		
		ITlfEngineHolder.ENGINE.registerNamedRenderer(new NamedIntRenderer());
		ITlfEngineHolder.ENGINE.registerNamedRenderer(new NamedDecimalRenderer());
		
		TlfPart.header = textFileReader.readTemplate(TEMPLATE_PATH + TlfPart.header);
		TlfPart.footer = textFileReader.readTemplate(TEMPLATE_PATH + TlfPart.footer);
		
		CamPartSide.pargen = textFileReader.readTemplate(TEMPLATE_PATH + CamPartSide.pargen);
		CamPartSide.entities = textFileReader.readTemplate(TEMPLATE_PATH + CamPartSide.entities);
		CamPartSide.works = textFileReader.readTemplate(TEMPLATE_PATH + CamPartSide.works);
		
		Drilling.entity = textFileReader.readTemplate(TEMPLATE_PATH + Drilling.entity);
		Drilling.work = textFileReader.readTemplate(TEMPLATE_PATH + Drilling.work);
		
		RowDrilling.entity = textFileReader.readTemplate(TEMPLATE_PATH + RowDrilling.entity);
		RowDrilling.work = textFileReader.readTemplate(TEMPLATE_PATH + RowDrilling.work);
		
		TlfEngine.initialized = true;
	}
	
}
