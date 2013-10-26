package br.sptrans.transportepublico.servico;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import br.sptrans.transportepublico.identificador.EmpresaIdentificador;
import br.sptrans.transportepublico.mapeamento.RotaMapeamento;
import br.sptrans.transportepublico.modelo.RotaModelo;
import br.sptrans.transportepublico.usuario.R;
import br.sptrans.transportepublico.webservice.WebService;

import com.google.android.gms.maps.model.LatLng;

public class RotaServico {

	private Activity _activity;
	
	public RotaServico(Activity activity)
	{
		_activity = activity;
	}
	
	public RotaModelo pesquisaRota(String id,EmpresaIdentificador empresaIdentificador)
	{
		String url = _activity.getText(R.string.webservice_pesquisaRota).toString();
		url = url.replace("@OlhoVivoId", id);
		url = url.replace("@Empresa", String.valueOf(empresaIdentificador.getValor()));
		String json = new WebService(_activity, url).webGet();
		
		return new RotaMapeamento().deJsonParaRotaModelo(json);
	}
	
	public List<LatLng> transformaRotaParaCoordenadas(RotaModelo rotaModelo)
	{
		if(rotaModelo == null)
			return new ArrayList<LatLng>();
		
		return new RotaMapeamento().deRotaParaLatLng(rotaModelo.getCoordenates());	
	}
}
