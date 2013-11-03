package br.sptrans.transportepublico.banco;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {
	
		protected SQLiteDatabase db;
		private String NOME_BANCO = "painel.db";
		private int VERSAO_BANCO = 1;
		
		public BancoDados(Context context,String[] scriptCriarDB, String scriptApagarDB) {
			BancoDadosHelper sh = new BancoDadosHelper(context, VERSAO_BANCO,NOME_BANCO, scriptCriarDB, scriptApagarDB);
			sh.getWritableDatabase();
			
			if(db != null)
			{
				if(db.isOpen())
					db.close();
			}
			
			sh.close();
		}
		
		public BancoDados(Context context) {
			// TODO Auto-generated constructor stub
			if(db != null)
			{
				if(db.isOpen())
					db.close();
			}
			
			db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
		}
		
		protected long insert(ContentValues values,String nomeTabela)
		{
			long value =  db.insert(nomeTabela, null, values);
			db.close();
			return value;
		}
		
		protected long update(String nomeTabela,ContentValues valores, String where, String[] whereArgs)
		{
			try
			{
				return db.update(nomeTabela, valores, where, whereArgs);
				
			}
			catch (Exception e) {
				// TODO: handle exception
				return 0;
			}
		}
		
		protected long delete(String nomeTabela,String where)
		{
			long value = db.delete(nomeTabela, where, null);
			db.close();
			return value;
		}
		
		protected Cursor pesquisaPorColuna(String table,String idColumn,String id) {
			
			Cursor c = null;
			try
			{
				c = db.query(table,null, idColumn + " = '" + id + "'", null, null, null, null);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			return c;
		}
		
		protected Cursor pesquisaTudo(String tabela)
		{
			Cursor c = null;
			try
			{
				c = db.query(tabela,null, null, null, null, null, null);
				return c;
			}
			catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}	
}