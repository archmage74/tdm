package tdm.cam.export;

import java.io.File;
import java.util.List;

import tdm.cam.db.imos.IImosService;
import tdm.cam.tlf.CamPart;
import tdm.cam.tlf.TlfDocument;
import tdm.cam.util.TextFileWriter;

public class Exporter {

	private TextFileWriter textFileWriter = new TextFileWriter();
	private IImosService imosService;

	private String exportPath = ".";

	public void export(String orderId) {
		List<CamPart> camParts = imosService.readParts(orderId);

		File exportDir = createEmptySubfolder(orderId);

		for (CamPart camPart : camParts) {
			for (TlfDocument doc : camPart.createTlfDocuments()) {
				textFileWriter.writeEscapedTlf(doc.getTlf(), exportDir.getPath() + '/' + doc.getName());
			}
		}
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
