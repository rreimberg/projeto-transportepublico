package br.sptrans.transportepublico.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BancoDadosHelper extends SQLiteOpenHelper{

	public BancoDadosHelper(Context context,int versao, String nomeBanco,String[] scriptCriarDB, String scriptApagarDB) {
		super(context, nomeBanco, null, versao);
		sqls = scriptCriarDB;
		ctx = context;
		_nameBase = nomeBanco;
	}
	protected SQLiteDatabase db;
	protected String[] sqls;
	protected Context ctx;
	protected String _nameBase;
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		for (int i = 0; i < sqls.length; i++) {
			ExecScripts(db,sqls[i]);
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	private void ExecScripts(SQLiteDatabase db,String sqls)
	{
		db.beginTransaction();
		
		try
		{
			for (int i = 0; i < sqls.split(";").length; i++) {
				String sql = sqls.split(";")[i];
				Log.i("Script", sql);
				
				if(sql.length() > 0)
					db.execSQL(sql);
			}
			
			db.setTransactionSuccessful();
		}
		finally{
			db.endTransaction();
		}		
	}
}
