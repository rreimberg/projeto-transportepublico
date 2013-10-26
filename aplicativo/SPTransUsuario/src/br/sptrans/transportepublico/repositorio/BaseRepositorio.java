package br.sptrans.transportepublico.repositorio;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.sptrans.transportepublico.banco.BancoDados;

public abstract class BaseRepositorio<TModelo,TTipo> extends BancoDados{

	public BaseRepositorio(Context context) {
		super(context);
	}
	
	
	public abstract void inserir(TModelo modelo);
	public abstract void atualizarPorId(TModelo modelo);
	public abstract TModelo pesquisaPorId(TTipo id);
	public abstract List<TModelo> pesquisarPor(String campo,String valor);
	public abstract List<TModelo> pesquisarPor(String campo,int valor);
	public abstract void apaga(TTipo id);
	public abstract List<TModelo> pesquisarTudo();
	public abstract ContentValues converterParaContentValues(TModelo modelo);
	public abstract List<TModelo> converterDeCursor(Cursor cursor);
	
	public String retornaParametro(String campo,TTipo valor)
	{
		return String.format("%s=%s", campo,valor);
	}
}
