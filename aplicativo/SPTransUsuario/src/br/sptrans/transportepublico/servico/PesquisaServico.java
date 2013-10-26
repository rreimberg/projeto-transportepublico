package br.sptrans.transportepublico.servico;

import java.util.List;

import android.app.Activity;
import br.sptrans.transportepublico.mapeamento.PesquisaMapeamento;
import br.sptrans.transportepublico.modelo.PesquisaModelo;
import br.sptrans.transportepublico.usuario.R;
import br.sptrans.transportepublico.webservice.WebService;

public class PesquisaServico {
	
	private Activity _activity;
	
	public PesquisaServico(Activity activity) {
		_activity = activity;
	}

	public List<PesquisaModelo> retornaPesquisa(String filtro)
	{	
		String url = _activity.getText((R.string.webservice_pesquisaLinha)).toString();
		url = url.replace("@Linha", filtro);
		url = url.replace("@ApplicationId", "1");
		url = url.replace("@DeviceId", "1");
		
		String json = new WebService(_activity, url).webGet();
		return new PesquisaMapeamento().deJsonParaModelo(json);
	}
}
