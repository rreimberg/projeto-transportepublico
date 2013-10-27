package br.sptrans.transportepublico.servico;

import java.util.List;

import android.app.Activity;
import br.sptrans.transportepublico.mapeamento.NoticiasMapeamento;
import br.sptrans.transportepublico.modelo.NoticiaModelo;
import br.sptrans.transportepublico.usuario.R;
import br.sptrans.transportepublico.webservice.WebService;

public class NoticiasServico {

	private Activity _activity;
	
	public NoticiasServico(Activity activity) {
		_activity = activity;
	}

	public List<NoticiaModelo> pesquisaNoticias()
	{	
		String url = _activity.getText((R.string.webservice_PesquisaNoticias)).toString();
		
		String json = new WebService(_activity, url).webGet();
		return new NoticiasMapeamento().deJson(json);
	}
}
