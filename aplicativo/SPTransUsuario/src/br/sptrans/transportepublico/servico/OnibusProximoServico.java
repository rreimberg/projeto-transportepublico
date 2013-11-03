package br.sptrans.transportepublico.servico;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import br.sptrans.transportepublico.identificador.EmpresaIdentificador;
import br.sptrans.transportepublico.identificador.SentidoIdentificador;
import br.sptrans.transportepublico.mapeamento.OnibusProximoMapeamento;
import br.sptrans.transportepublico.modelo.OnibusProximoModelo;
import br.sptrans.transportepublico.usuario.R;
import br.sptrans.transportepublico.webservice.WebService;

public class OnibusProximoServico {

	private Activity _activity;
	private Context _context;
	public OnibusProximoServico(Activity activity)
	{
		_activity = activity;
	}
	
	public OnibusProximoServico(Context context)
	{
		_context = context;
	}
	
	public OnibusProximoModelo pesquisaOnibusProximo(String ids,int pontoId)
	{
		if(ids == null || ids.length() == 0)
			return null;
		
		ids = ids.substring(0,ids.length() - 1);
		String url = "";
		
		if(_activity != null)
			url = _activity.getText(R.string.webservice_PesquisaOnibusProximo).toString();
		else
			url = _context.getText(R.string.webservice_PesquisaOnibusProximo).toString();
		
		url = url.replace("@OlhoVivoId", ids);
		url = url.replace("@Empresa",String.valueOf(EmpresaIdentificador.SPTrans.getValor()));
		url = url.replace("@Sentido",String.valueOf(SentidoIdentificador.Bairro.getValor()));
		url = url.replace("@StopId", String.valueOf(pontoId));
		
		String json = "";	
		
		if(_activity != null)
			json = new WebService(_activity, url).webGet();
		else
			json = new WebService(_context, url).webGet();
		
		Log.e("Cooo", url);
		Log.e("Cooo", json);
		return new OnibusProximoMapeamento().deJson(json);
	}
}
