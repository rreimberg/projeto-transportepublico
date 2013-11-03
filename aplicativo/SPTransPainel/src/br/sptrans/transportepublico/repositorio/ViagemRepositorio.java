package br.sptrans.transportepublico.repositorio;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.sptrans.transportepublico.modelo.ViagemModelo;
import br.sptrans.transportepublico.repositorio.tabela.ViagemTabela;

public class ViagemRepositorio extends BaseRepositorio<ViagemModelo, Integer>{

	public ViagemRepositorio(Context context) {
		super(context);
	}

	@Override
	public void inserir(ViagemModelo modelo) {
		insert(converterParaContentValues(modelo), new ViagemTabela().nome());
	}

	@Override
	public void atualizarPorId(ViagemModelo modelo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViagemModelo pesquisaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViagemModelo> pesquisarPor(String campo, String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViagemModelo> pesquisarPor(String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void apaga(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViagemModelo> pesquisarTudo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentValues converterParaContentValues(ViagemModelo modelo) {
		ContentValues values = new ContentValues();
		values.put(ViagemTabela.CobradorId, modelo.getCobradorId());
		values.put(ViagemTabela.LinhaId, modelo.getLinhaId());
		values.put(ViagemTabela.MacAddress, modelo.getMacAddress());
		values.put(ViagemTabela.MotoristaId, modelo.getMotoristaId());
		values.put(ViagemTabela.PrefixoOnibus, modelo.getPrefixoOnibus());
		values.put(ViagemTabela.ViagemGuid, modelo.getViagemGuid());
		return values;
	}

	@Override
	public List<ViagemModelo> converterDeCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}
}
