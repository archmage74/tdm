package tdm.cam.imos.db;

import tdm.cam.model.imos.ImosProject;

public interface IImosService {

	public void init();
	
	public ImosProject readProject(String orderId);

}