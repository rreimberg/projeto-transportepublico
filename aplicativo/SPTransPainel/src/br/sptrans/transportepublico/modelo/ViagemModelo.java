package br.sptrans.transportepublico.modelo;

public class ViagemModelo {

	private int Id;
	private String ViagemGuid ;
	private int LinhaId;
	private int PrefixoOnibus ;
	private int CobradorId ;
	private int MotoristaId;
	private String MacAddress ;
	
	public ViagemModelo(int id,String viagemGuid,int linhaId,int prefixoOnibus,
			int cobradorId,int motoristaId,String macAddress)
	{
		Id = id;
		ViagemGuid = viagemGuid;
		LinhaId = linhaId;
		PrefixoOnibus = prefixoOnibus;
		CobradorId = cobradorId;
		MotoristaId = motoristaId;
		MacAddress = macAddress;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getViagemGuid() {
		return ViagemGuid;
	}
	public void setViagemGuid(String viagem) {
		ViagemGuid = viagem;
	}
	public int getLinhaId() {
		return LinhaId;
	}
	public void setLinhaId(int linhaId) {
		LinhaId = linhaId;
	}
	public int getPrefixoOnibus() {
		return PrefixoOnibus;
	}
	public void setPrefixoOnibus(int prefixoOnibus) {
		PrefixoOnibus = prefixoOnibus;
	}
	public int getCobradorId() {
		return CobradorId;
	}
	public void setCobradorId(int cobradorId) {
		CobradorId = cobradorId;
	}
	public int getMotoristaId() {
		return MotoristaId;
	}
	public void setMotoristaId(int motoristaId) {
		MotoristaId = motoristaId;
	}
	public String getMacAddress() {
		return MacAddress;
	}
	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}
}
