package tdm.cam.ui.client;

import tdm.cam.model.imos.ImosProject;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ImosServiceAsync {

	void readProject(String orderId, AsyncCallback<ImosProject> callback);

}
