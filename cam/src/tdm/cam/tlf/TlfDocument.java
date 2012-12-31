package tdm.cam.tlf;

public class TlfDocument {

	public static final String FRONT_SIDE_SUFFIX = "_frontside.tlf";
	public static final String BACK_SIDE_SUFFIX = "_backside.tlf";
	
	private String name;
	private String tlf;

	public TlfDocument(String name, String tlf) {
		this.name = name;
		this.tlf = tlf;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tlf == null) ? 0 : tlf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TlfDocument other = (TlfDocument) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tlf == null) {
			if (other.tlf != null)
				return false;
		} else if (!tlf.equals(other.tlf))
			return false;
		return true;
	}

}
