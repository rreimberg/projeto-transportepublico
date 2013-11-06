package br.sptrans.transportepublico.banco;

public class BancoDadosScript {

	public static String[] CreateTables()
	{
		return new String[]{
				CriarTabelas(),
		};
	}
	
	public static String CriarTabelas()
	{
		String sql = "";
		
		//inicio e fim (timestamp), contador, linha, sentido
		sql = "CREATE TABLE tbSPT_Viagem " +
			"(PK_int_Viagem INTEGER  PRIMARY KEY," +
			"vch_ViagemGUID varchar(50) NOT NULL," +
			"FK_int_LinhaId int NULL," +
			"int_PrefixoOnibus int NULL," +
			"int_CobradorId int NULL," +
			"int_MotoristaId int NULL, " +
			"vch_MacAddress varchar(250) NULL);";
		
		sql = sql + "CREATE TABLE tbSPT_ViagemAlerta(" +
			"PK_int_ViagemAlerta INTEGER  PRIMARY KEY, " +
			"FK_vch_ViagemGUID varchar(50) NOT NULL," +
			"FK_int_Alerta int NOT NULL," +
			"vch_Observacao varchar(8000) NULL," +
			"dtt_Inclusao datetime NOT NULL," +
			"vch_Latitude varchar(50) NULL," +
			"vch_Longitude varchar(50) NULL);";	
		
		sql = sql + "CREATE TABLE tbSPT_ViagemHistorico(" +
			"PK_int_ViagemHistorico INTEGER PRIMARY KEY, " +
			"FK_vch_ViagemGUID varchar(500) NOT NULL, " +
			"int_ContadorDaCatraca int NULL, " +
			"int_Status int NULL, " +
			"FK_int_Motivo int NULL, " +
			"vch_Observacao varchar(500) NULL, " +
			"dtt_Historico datetime NOT NULL, "  +
			"vch_Latitude varchar(50) NULL, " +
			"vch_Longitude varchar(50) NULL);";			
		
		return sql;
	}
}