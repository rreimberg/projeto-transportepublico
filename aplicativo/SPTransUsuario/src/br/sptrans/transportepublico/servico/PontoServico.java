package br.sptrans.transportepublico.servico;

import java.util.List;

import android.app.Activity;
import br.sptrans.transportepublico.identificador.SentidoIdentificador;
import br.sptrans.transportepublico.mapeamento.PontoMapeamento;
import br.sptrans.transportepublico.modelo.PontoLinhaModelo;
import br.sptrans.transportepublico.modelo.PontoModelo;
import br.sptrans.transportepublico.usuario.R;
import br.sptrans.transportepublico.webservice.WebService;

public class PontoServico {

	private Activity _activity;
	public PontoServico(Activity activity)
	{
		_activity = activity;
	}
	
	public List<PontoModelo> pesquisaPontos(String latitude,String longitude,SentidoIdentificador sentidoIdentificador,int distancia)
	{
		String url = _activity.getText(R.string.webservice_PesquisaPontosPorCoordenadas).toString();
		url = url.replace("@Latitude", latitude);
		url = url.replace("@Longitude", longitude);
		url = url.replace("@Direction", String.valueOf(sentidoIdentificador.getValor()));
		url = url.replace("@distanceMax", String.valueOf(distancia));
				
		String result = new WebService(_activity, url).webGet();
		return new PontoMapeamento().deJsonParaPontoModelo(result);
	}
	
	public List<PontoLinhaModelo> pesquisaLinhaPorPonto(int codigoPonto)
	{
		String url = _activity.getText(R.string.webservice_pesquisaOnibusPorPonto).toString();
		url = url.replace("@stopId", String.valueOf( codigoPonto));
				
		String result = new WebService(_activity, url).webGet();
		return new PontoMapeamento().deJsonParaPontoLinhaModelo(result);
	}
}
