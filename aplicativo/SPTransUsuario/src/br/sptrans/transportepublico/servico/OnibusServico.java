package br.sptrans.transportepublico.servico;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import br.sptrans.transportepublico.identificador.EmpresaIdentificador;
import br.sptrans.transportepublico.identificador.SentidoIdentificador;
import br.sptrans.transportepublico.mapeamento.OnibusMapeamento;
import br.sptrans.transportepublico.modelo.OnibusModelo;
import br.sptrans.transportepublico.usuario.R;
import br.sptrans.transportepublico.webservice.WebService;

public class OnibusServico {

	private Activity _activity;
	private Context _context;
	public OnibusServico(Activity activity)
	{
		_activity = activity;
	}
	
	public OnibusServico(Context context)
	{
		_context = context;
	}
	
	public List<OnibusModelo> pesquisaOnibus(String id,EmpresaIdentificador empresa,SentidoIdentificador sentido)
	{
		String url = "";
		
		if(_activity != null)
			url = _activity.getText(R.string.webservice_pesquisaPosicao).toString();
		else
			url = _context.getText(R.string.webservice_pesquisaPosicao).toString();
		
		url = url.replace("@OlhoVivoId", id);
		url = url.replace("@Empresa", String.valueOf(empresa.getValor()));
		url = url.replace("@Sentido", String.valueOf(sentido.getValor()));
		
		String json = "";
		
		if(_activity != null)
			json = new WebService(_activity, url).webGet();
		else
			json = new WebService(_context, url).webGet();
		
		
		OnibusMapeamento onibusMapeamento = new OnibusMapeamento();
		return onibusMapeamento.deJsonParaOnibusModelo(json);
	}
}
