package tdm.cam.ui.client.prj;

import java.util.Comparator;

public class AlphabeticalPartListOrder implements Comparator<Part> {

	@Override
	public int compare(Part o1, Part o2) {
		
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			} else {
				return 1;
			}
		}
		if (o2 == null) {
			return -1;
		}
		
		String barcode1 = o1.getOriginalImosPart().getBarcode();
		String barcode2 = o2.getOriginalImosPart().getBarcode();
		if (barcode1 == null) {
			if (barcode2 == null) {
				return 0;
			} else {
				return 1;
			}
		}
		if (barcode2 == null) {
			return -1;
		}
		
		return barcode1.compareTo(barcode2);
	}

}
