package br.sptrans.transportepublico.repositorio.tabela;


public class AlertaViagem extends BaseTabela{

	public static String Id = "PK_int_ViagemAlerta";
	public static String ViagemId = "FK_vch_ViagemGUID";
	public static String AlertaId = "FK_int_Alerta";
	public static String Observacao = "vch_Observacao";
	public static String DataInclusao = "dtt_Inclusao";
	public static String Latitude = "vch_Latitude";
	public static String Longitude = "vch_Longitude";
	
	@Override
	public String nome() {
		return "tbSPT_ViagemAlerta";
	}

	@Override
	public String id() {
		return Id;
	}
}
