package tdm.cam.ui.client;

import com.google.gwt.i18n.client.Messages;

public interface UIMessages extends Messages {
	
	@DefaultMessage("{0,number,#,###.00}")
	String mm(double measure);
	
}
