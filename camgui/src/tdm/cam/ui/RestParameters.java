package tdm.cam.ui;

import java.util.ArrayList;
import java.util.List;

public class RestParameters {
	
	private List<String> params = new ArrayList<String>();
	
	public RestParameters addParam(String param) {
		params.add(param);
		return this;
	}
	
	public List<String> getParams() {
		return params;
	}
	
}
