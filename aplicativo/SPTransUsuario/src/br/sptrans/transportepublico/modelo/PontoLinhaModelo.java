package br.sptrans.transportepublico.modelo;

public class PontoLinhaModelo {
	private int Id;
	private String Prefix;
	private String PrefixType;
	private int Direction;
	private String OlhoVivoId;
	private String Target;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPrefix() {
		return Prefix;
	}
	public void setPrefix(String prefix) {
		Prefix = prefix;
	}
	public String getPrefixType() {
		return PrefixType;
	}
	public void setPrefixType(String prefixType) {
		PrefixType = prefixType;
	}
	public int getDirection() {
		return Direction;
	}
	public void setDirection(int direction) {
		Direction = direction;
	}
	
	public String getPrefixAndType()
	{
		return getPrefix() + "-" + getPrefixType();
	}
	public String getOlhoVivoId() {
		return OlhoVivoId;
	}
	public void setOlhoVivoId(String olhoVivoId) {
		OlhoVivoId = olhoVivoId;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
}
