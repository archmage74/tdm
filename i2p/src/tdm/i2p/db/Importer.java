package tdm.i2p.db;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.widgets.Text;

import tdm.i2p.db.imos.IMOSPart;
import tdm.i2p.db.imos.IMOSPartProfile;
import tdm.i2p.db.imos.IMOSPartService;
import tdm.i2p.db.imos.IMOSProperties;
import tdm.i2p.db.protime.ProtimePart;
import tdm.i2p.db.protime.ProtimeProperties;
import tdm.i2p.db.protime.ProtimeService;

public class Importer {
	
	private Text messageBox;
	
	private Collection<IMOSPart> imosPartList = null;
	
	private IMOSPartService imosPartService;
	private ProtimeService protimeService;
	
	public void readFromImos(String imosName) {
		Connection imosConnection;
		
		try {
			imosConnection = ConnectionProvider.getConnection(new IMOSProperties());
			imosPartService = new IMOSPartService();
			imosPartService.setDbConnection(imosConnection);
			
			//"09JAIDH_KUECHE_new"
			imosPartList = imosPartService.readParts(imosName);
			imosPartService.validateParts(imosPartList);
			imosPartService.assignSPos(imosName, imosPartList);
			
			for (IMOSPart part : imosPartList) {
				System.out.println(part);
			}
		} catch(Exception e) {
			messageBox.append("Datenbank Fehler: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Collection<String> getMainMaterials() {
		Set<String> mats = new HashSet<String>();
		for (IMOSPart part : imosPartList) {
			mats.add(part.getMatId());
		}
		return mats;
	}

	public Collection<String> getProfileMaterials() {
		Set<String> profileMats = new HashSet<String>();
		for (IMOSPart part : imosPartList) {
			for (IMOSPartProfile profile : part.getProfileMap().values()) {
				profileMats.add(profile.getPrfId());
			}
		}
		return profileMats;
	}

	public Collection<String> getSurfaceMaterials() {
		Set<String> surfaceMats = new HashSet<String>();
		for (IMOSPart part : imosPartList) {
			surfaceMats.add(part.getSurfTId());
			surfaceMats.add(part.getSurfBId());
		}
		return surfaceMats;
	}

	public void writeToProtime(String protimeName, int hpos, Collection<String> materials) {
		Connection protimeConnection;

		try {
			protimeConnection = ConnectionProvider.getConnection(new ProtimeProperties());
			protimeService = new ProtimeService();
			protimeService.setDbConnection(protimeConnection);

			// "TEST_10"
			Collection<ProtimePart> protimePartList = protimeService.convert(protimeName, hpos, imosPartList, materials);
			protimeService.preprocessParts(protimePartList);
			protimeService.writePosDescription(protimeName, hpos, imosPartService.getSPosMap().values());
			
			for (ProtimePart part : protimePartList) {
				// ProtimePart part = protimePartList.iterator().next();
				protimeService.writePartDescription(part);
				protimeService.writePart(part);
				System.out.println((new Date()) + ": wrote protime part: " + part.BEZEICHNUNG);
			}
			messageBox.append("Protime Import abgeschlossen\n");
			if (imosPartService.getFailureParts().size() > 0) {
				messageBox.append("Fehlerhafte IMOS-Parts:\n");
				for (IMOSPart part : imosPartService.getFailureParts()) {
					messageBox.append("** " + part + "\n\n");
				}
			}
		} catch(Exception e) {
			messageBox.append("Datenbank Fehler: " + e.getMessage());
//			System.out.println("Unable to load the driver class!");
			e.printStackTrace();
		}
		
	}

	public Text getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(Text messageBox) {
		this.messageBox = messageBox;
	}

}
