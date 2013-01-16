package tdm.cam.imos.db;

import java.util.List;

import tdm.cam.tlf.TlfPart;

public interface IImosService {

	public abstract List<TlfPart> readParts(String orderId);

}