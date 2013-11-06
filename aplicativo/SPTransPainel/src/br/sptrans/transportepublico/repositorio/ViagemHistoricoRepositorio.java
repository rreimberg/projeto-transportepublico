package br.sptrans.transportepublico.repositorio;

import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.sptrans.transportepublico.modelo.ViagemHistoricoModelo;
import br.sptrans.transportepublico.repositorio.tabela.ViagemHistoricoTabela;

public class ViagemHistoricoRepositorio extends BaseRepositorio<ViagemHistoricoModelo, Integer>{

	public ViagemHistoricoRepositorio(Context context) {
		super(context);
	}

	@Override
	public void inserir(ViagemHistoricoModelo modelo) {
		insert(converterParaContentValues(modelo), new ViagemHistoricoTabela().nome());
	}

	@Override
	public void atualizarPorId(ViagemHistoricoModelo modelo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ViagemHistoricoModelo pesquisaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViagemHistoricoModelo> pesquisarPor(String campo, String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViagemHistoricoModelo> pesquisarPor(String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void apaga(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViagemHistoricoModelo> pesquisarTudo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentValues converterParaContentValues(ViagemHistoricoModelo modelo) {
		ContentValues values = new ContentValues();
		values.put(ViagemHistoricoTabela.ContadorCatraca, modelo.pegaContadorCatraca());
		values.put(ViagemHistoricoTabela.DataHistorico, modelo.pegaDataHistorico().toString());
		values.put(ViagemHistoricoTabela.Latitude, modelo.pegaLatitude());
		values.put(ViagemHistoricoTabela.Longitude, modelo.pegaLongitude());
		values.put(ViagemHistoricoTabela.Motivo, modelo.pegaMotivo());
		values.put(ViagemHistoricoTabela.Observacao, modelo.pegaObservacao());
		values.put(ViagemHistoricoTabela.StatusViagem, modelo.pegaStatusViagem());
		values.put(ViagemHistoricoTabela.ViagemGuid, modelo.pegaViagemGuid());
		return values;
	}

	@Override
	public List<ViagemHistoricoModelo> converterDeCursor(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}
}
