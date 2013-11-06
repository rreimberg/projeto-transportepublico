package br.sptrans.transportepublico.repositorio.tabela;


public class ViagemTabela extends BaseTabela{

	public static String Id = "PK_int_Viagem";
	public static String ViagemGuid = "vch_ViagemGUID";
	public static String LinhaId = "FK_int_LinhaId";
	public static String PrefixoOnibus = "int_PrefixoOnibus";
	public static String CobradorId = "int_CobradorId";
	public static String MotoristaId = "int_MotoristaId";
	public static String MacAddress = "vch_MacAddress";
	@Override
	public String nome() {
		return "tbSPT_Viagem";
	}
	@Override
	public String id() {
		return Id;
	}
}
