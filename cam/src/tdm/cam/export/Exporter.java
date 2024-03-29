package tdm.cam.export;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tdm.cam.imos.db.IImosService;
import tdm.cam.model.imos.ImosProject;
import tdm.cam.tlf.TlfDocument;
import tdm.cam.tlf.TlfPart;
import tdm.cam.tlf.imos2tlf.Imos2TlfConverter;
import tdm.cam.util.TextFileWriter;

public class Exporter {

	private TextFileWriter textFileWriter = new TextFileWriter();
	private Imos2TlfConverter imos2Tlf = new Imos2TlfConverter();
	private IImosService imosService;

	private String exportPath = ".";

	public String export(String orderId, Map<String, Integer> rotationMap) {
		ImosProject imosProject = imosService.readProject(orderId);
		Collection<TlfPart> tlfParts = imos2Tlf.convert(imosProject, rotationMap);
		
		File exportDir = createEmptySubfolder(orderId);
		
		StringBuffer warnings = new StringBuffer("");
		for (TlfPart camPart : tlfParts) {
			for (String warning : camPart.getConvertionWarnings()) {
				warnings.append("[").append(camPart.getBarcode()).append("] ").append(warning);
			}
			for (TlfDocument doc : camPart.createTlfDocuments()) {
				textFileWriter.writeEscapedTlf(doc.getTlf(), exportDir.getPath() + "/" + doc.getName());
			}
		}
		return warnings.toString();
	}

	public String export(String orderId) {
		return export(orderId, new HashMap<String, Integer>());
	}
	
	private File createEmptySubfolder(String orderId) {
		File exportDir = new File(exportPath + orderId);
		if (exportDir.exists()) {
			if (exportDir.isDirectory()) {
				removeAllFilesFromDir(exportDir);
			} else {
				throw new RuntimeException("a file with the name '" + exportDir + "' exists already in the export directory");
			}
		} else {
			exportDir.mkdirs();
		}
		return exportDir;
	}

	private void removeAllFilesFromDir(File dir) {
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				removeAllFilesFromDir(f);
			} else {
				f.delete();
			}
		}
	}

	public IImosService getImosService() {
		return imosService;
	}

	public void setImosService(IImosService imosService) {
		this.imosService = imosService;
	}

	public String getExportPath() {
		return exportPath;
	}

	public void setExportPath(String exportPath) {
		this.exportPath = new String(exportPath);
		exportPath = exportPath.replace('\\', '/');
		if (this.exportPath.charAt(this.exportPath.length() - 1) != '/') {
			this.exportPath += '/';
		}
	}

}
