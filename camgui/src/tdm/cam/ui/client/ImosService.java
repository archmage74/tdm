package tdm.cam.ui.client;

import tdm.cam.model.imos.ImosProject;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("imos")
public interface ImosService extends RemoteService {

	ImosProject readProject(String orderId) throws IllegalArgumentException;

	void exportTlf(String orderId);
	
}
