package tdm.i2p.db.protime;

public class SPosEntry {

	Integer sPos;
	String description;

	public SPosEntry(Integer sPos, String description) {
		this.sPos = sPos;
		this.description = description;
	}
	
	public Integer getSPos() {
		return sPos;
	}
	public void setsPos(Integer sPos) {
		this.sPos = sPos;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "SPosEntry { sPos=" + sPos + ", description=" + description + " }"; 
	}
}
