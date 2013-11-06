package br.sptrans.transportepublico.repositorio.tabela;


public class ViagemHistoricoTabela extends BaseTabela{

	public static String Id = "PK_int_ViagemHistorico";
	public static String ViagemGuid = "FK_vch_ViagemGUID";
	public static String ContadorCatraca = "int_ContadorDaCatraca";
	public static String StatusViagem = "int_Status";
	public static String Motivo = "FK_int_Motivo";
	public static String Observacao = "vch_Observacao";
	public static String DataHistorico = "dtt_Historico";
	public static String Latitude = "vch_Latitude";
	public static String Longitude = "vch_Longitude";
	
	@Override
	public String nome() {
		return "tbSPT_ViagemHistorico";
	}

	@Override
	public String id() {
		return Id;
	}
}
