package tdm.cam.ui.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ExportResult implements IsSerializable {
	
	protected ExportResultType result;
	
	protected String details;

	public ExportResult() {
		
	}
	
	public ExportResult(ExportResultType result, String details) {
		this.result = result;
		this.details = details;
	}

	public ExportResultType getResult() {
		return result;
	}

	public void setResult(ExportResultType result) {
		this.result = result;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
