package tdm.cam.tlf.imos2tlf.filter;

import java.util.List;

import tdm.cam.model.imos.ImosDrilling;

public class DiagonalFailAndWarningFilter extends DiagonalFailFilter {

	protected final String WARNING_FORMAT_STRING = "Schraegbohrung gefunden: %1s wird ignoriert";
	
	protected List<String> warnings;
	
	public DiagonalFailAndWarningFilter(List<String> warnings) {
		this.warnings = warnings;
	}
	
	@Override
	public ImosDrilling filter(ImosDrilling drilling) {
		ImosDrilling result = super.filter(drilling);
		if (result == null) {
			warnings.add(String.format(WARNING_FORMAT_STRING, drilling.toString()));
		}
		return result;
	}
	
}
