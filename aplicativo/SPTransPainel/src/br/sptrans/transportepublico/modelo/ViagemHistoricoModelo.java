package br.sptrans.transportepublico.modelo;

import java.sql.Date;

public class ViagemHistoricoModelo {

	private int Id;
	private String ViagemGuid;
	private int ContadorCatraca;
	private int StatusViagem;
	private int Motivo;
	private String Observacao;
	private Date DataHistorico;
	private String Latitude;
	private String Longitude;
	
	public int pegaId() {
		return Id;
	}
	public void defineId(int id) {
		Id = id;
	}
	public String pegaViagemGuid() {
		return ViagemGuid;
	}
	public void setaViagemGuid(String viagemGuid) {
		ViagemGuid = viagemGuid;
	}
	public int pegaContadorCatraca() {
		return ContadorCatraca;
	}
	public void setaContadorCatraca(int contadorCatraca) {
		ContadorCatraca = contadorCatraca;
	}
	public int pegaStatusViagem() {
		return StatusViagem;
	}
	public void setaStatusViagem(int statusViagem) {
		StatusViagem = statusViagem;
	}
	public int pegaMotivo() {
		return Motivo;
	}
	public void setaMotivo(int motivo) {
		Motivo = motivo;
	}
	public String pegaObservacao() {
		return Observacao;
	}
	public void setaObservacao(String observacao) {
		Observacao = observacao;
	}
	public Date pegaDataHistorico() {
		return DataHistorico;
	}
	public void setaDataHistorico(Date dataHistorico) {
		DataHistorico = dataHistorico;
	}
	public String pegaLatitude() {
		return Latitude;
	}
	public void setaLatitude(String latitude) {
		Latitude = latitude;
	}
	public String pegaLongitude() {
		return Longitude;
	}
	public void setaLongitude(String longitude) {
		Longitude = longitude;
	}
	
	
}
