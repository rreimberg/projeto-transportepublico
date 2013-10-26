package br.sptrans.transportepublico.banco;

public class BancoDadosScript {

	public static String[] CreateTables()
	{
		return new String[]{
				CriarTbFavoritos(),
		};
	}
	
	public static String CriarTbFavoritos()
	{
		String sql = "";
		
		sql = "create table if not exists tbFavorito " +
				"(pk_vch_codigo_linha varchar(10)" +
				",vch_denominacaoTPTS text not null" +
				",vch_denominacaoTSTP text not null" +
				",int_sentido int not null" +
				",vch_tipo varchar(3) "+
				",vch_letreiro text not null " +
				",int_empresa int null)";
		
		return sql;
	}
}