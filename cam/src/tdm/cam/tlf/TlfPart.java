package tdm.cam.tlf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TlfPart implements ITlfNode {

	public static String header = "TlfDocument.header.jmte";
	public static String footer = "TlfDocument.footer.jmte";

	public List<TlfDocument> createTlfDocuments(CamPart camPart) {
		List<TlfDocument> docs = new ArrayList<TlfDocument>();
		if (!camPart.getFrontSide().isEmpty()) {
			String name = camPart.getBarcode() + TlfDocument.FRONT_SIDE_SUFFIX;
			String tlf = exportFrontSideTlf(camPart);
			docs.add(new TlfDocument(name, tlf));
		}
		if (!camPart.getBackSide().isEmpty()) {
			String name = camPart.getBarcode() + TlfDocument.BACK_SIDE_SUFFIX;
			String tlf = exportBackSideTlf(camPart);
			docs.add(new TlfDocument(name, tlf));
		}
		return docs;
	}
	
	public String exportFrontSideTlf(CamPart camPart) {
		StringBuffer tlf = new StringBuffer();
		Map<String, Object> docModel = new HashMap<String, Object>();
		tlf.append(ENGINE.transform(header, docModel));
		tlf.append(camPart.exportFrontSideTlf());
		tlf.append(ENGINE.transform(footer, docModel));
		return tlf.toString();
	}

	public String exportBackSideTlf(CamPart camPart) {
		StringBuffer tlf = new StringBuffer();
		Map<String, Object> docModel = new HashMap<String, Object>();
		tlf.append(ENGINE.transform(header, docModel));
		tlf.append(camPart.exportBackSideTlf());
		tlf.append(ENGINE.transform(footer, docModel));
		return tlf.toString();
	}

}
