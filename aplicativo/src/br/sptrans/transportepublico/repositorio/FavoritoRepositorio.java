package br.sptrans.transportepublico.repositorio;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.sptrans.transportepublico.modelo.FavoritoModelo;

public class FavoritoRepositorio extends BaseRepositorio<FavoritoModelo,String>{

	private FavoritoTabela tabela = null;
	public FavoritoRepositorio(Context context) {
		super(context);
		tabela =new FavoritoTabela();
	}

	@Override
	public void inserir(FavoritoModelo modelo) {
		insert(converterParaContentValues(modelo), tabela.nome());
	}

	@Override
	public void atualizarPorId(FavoritoModelo modelo) {
	}

	@Override
	public FavoritoModelo pesquisaPorId(String id) {
		Cursor cursor = pesquisaPorColuna(tabela.nome(),tabela.id() , id);
		List<FavoritoModelo> fs = converterDeCursor(cursor);
		
		if(fs.size() > 0)
			return fs.get(0);
		
		return null;
	}

	@Override
	public List<FavoritoModelo> pesquisarPor(String campo, String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoritoModelo> pesquisarPor(String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void apaga(String id) {
		delete(tabela.nome(), retornaParametro(tabela.id(), id));
	}

	@Override
	public List<FavoritoModelo> pesquisarTudo() {
		return converterDeCursor(pesquisaTudo(tabela.nome()));
	}

	@Override
	public ContentValues converterParaContentValues(FavoritoModelo modelo) {
		ContentValues values = new ContentValues();
		values.put(FavoritoTabela.Id, modelo.getCodigoLinha());
		values.put(FavoritoTabela.DenominacaoTPTS, modelo.getDenominacaoTPTS());
		values.put(FavoritoTabela.DenominacaoTSTP, modelo.getDenominacaoTSTP());
		values.put(FavoritoTabela.Letreiro, modelo.getLetreiro());
		values.put(FavoritoTabela.Sentido, modelo.getSentido());
		values.put(FavoritoTabela.Tipo, modelo.getTipo());
		values.put(FavoritoTabela.Empresa, modelo.getEmpresa());

		return values;		
	}
	
	public List<FavoritoModelo> converterDeCursor(Cursor cursor)
	{
		List<FavoritoModelo> favoritoModelos = new ArrayList<FavoritoModelo>();
		
		if(cursor == null)
			return favoritoModelos;
		
		if (cursor.moveToFirst()) {
			
			int idId = cursor.getColumnIndex(FavoritoTabela.Id);
			int idDenominacaoTPTS = cursor.getColumnIndex(FavoritoTabela.DenominacaoTPTS);
			int idDenominacaoTSTP = cursor.getColumnIndex(FavoritoTabela.DenominacaoTSTP);
			int idLetreiro = cursor.getColumnIndex(FavoritoTabela.Letreiro);
			int idSentido = cursor.getColumnIndex(FavoritoTabela.Sentido);
			int idTipo = cursor.getColumnIndex(FavoritoTabela.Tipo);
			int idEmpresa = cursor.getColumnIndex(FavoritoTabela.Empresa);

			do {
				FavoritoModelo favoritoModelo = new FavoritoModelo();
				
				favoritoModelo.setDenominacaoTPTS(cursor.getString(idDenominacaoTPTS));
				favoritoModelo.setDenominacaoTSTP(cursor.getString(idDenominacaoTSTP));
				favoritoModelo.setCodigoLinha(cursor.getString(idId));
				favoritoModelo.setLetreiro(cursor.getString(idLetreiro));
				favoritoModelo.setSentido(cursor.getInt(idSentido));
				favoritoModelo.setTipo(cursor.getString(idTipo));
				favoritoModelo.setEmpresa(cursor.getInt(idEmpresa));
				favoritoModelos.add(favoritoModelo);

			} while (cursor.moveToNext());
		}
		
		return favoritoModelos;
	}


}
