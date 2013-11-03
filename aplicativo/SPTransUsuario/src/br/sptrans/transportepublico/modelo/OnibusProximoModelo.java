package br.sptrans.transportepublico.modelo;

public class OnibusProximoModelo {

	private int BusPrefix;
	private String Distance;
	private String OlhoVivoId;
	private String NameSource;
	private String NameTarget;
	private String Prefix;
	private int PrefixType;
	private int BusType;
	
	public int getBusPrefix() {
		return BusPrefix;
	}
	public void setBusPrefix(int busPrefix) {
		BusPrefix = busPrefix;
	}
	public String getDistance() {
		
		if(Distance != null && Distance.length() > 5)
			return Distance.substring(0,5);

		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getOlhoVivoId() {
		return OlhoVivoId;
	}
	public void setOlhoVivoId(String olhoVivoId) {
		OlhoVivoId = olhoVivoId;
	}
	public String getNameSource() {
		return NameSource;
	}
	public void setNameSource(String nameSource) {
		NameSource = nameSource;
	}
	public String getNameTarget() {
		return NameTarget;
	}
	public void setNameTarget(String nameTarget) {
		NameTarget = nameTarget;
	}
	public String getPrefix() {
		return Prefix;
	}
	public void setPrefix(String prefix) {
		Prefix = prefix;
	}
	public int getPrefixType() {
		return PrefixType;
	}
	public void setPrefixType(int prefixType) {
		PrefixType = prefixType;
	}
	public int getBusType() {
		return BusType;
	}
	public void setBusType(int busType) {
		BusType = busType;
	}
}
