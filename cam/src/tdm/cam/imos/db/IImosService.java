package tdm.cam.imos.db;

import java.util.List;

import tdm.cam.imos.ImosPart;

public interface IImosService {

	public abstract List<ImosPart> readParts(String orderId);

}