package tdm.cam.imos.db;

public class UnsupportedProfileException extends RuntimeException {

	private static final long serialVersionUID = -4058262414967382440L;

	public UnsupportedProfileException() {
		super();
	}
	public UnsupportedProfileException(String message) {
		super(message);
	}
	public UnsupportedProfileException(Throwable t) {
		super(t);
	}
	public UnsupportedProfileException(String message, Throwable t) {
		super(message, t);
	}

}
