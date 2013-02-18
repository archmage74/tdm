package tdm.cam.ui.client;

import tdm.cam.model.cmd.RotationList;
import tdm.cam.model.imos.ImosProject;
import tdm.cam.ui.shared.ExportResult;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("imos")
public interface ImosService extends RemoteService {

	ImosProject readProject(String orderId) throws IllegalArgumentException;

	ExportResult exportTlf(String orderId, RotationList rotationList);
	
}
