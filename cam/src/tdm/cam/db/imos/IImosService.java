package tdm.cam.db.imos;

import java.util.List;

import tdm.cam.tlf.CamPart;

public interface IImosService {

	public abstract List<CamPart> readParts(String orderId);

}