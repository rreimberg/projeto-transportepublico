package br.sptrans.transportepublico.repositorio;

public class FavoritoTabela extends BaseTabela{

	public static String Id = "pk_vch_codigo_linha";
	public static String DenominacaoTPTS = "vch_denominacaoTPTS";
	public static String DenominacaoTSTP = "vch_denominacaoTSTP";
	public static String Letreiro = "vch_letreiro";
	public static String Sentido = "int_sentido";
	public static String Tipo = "vch_tipo";
	public static String Empresa = "int_empresa";
	
	@Override
	public String nome() {
		return "tbFavorito";
	}

	@Override
	public String id() {
		return Id;
	}
}
