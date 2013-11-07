package br.sptrans.transportepublico.servico;

import java.util.List;

import android.app.Activity;
import br.sptrans.transportepublico.mapeamento.LinhaMapeamento;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.modelo.ViagemModelo;
import br.sptrans.transportepublico.painel.R;
import br.sptrans.transportepublico.repositorio.ViagemRepositorio;
import br.sptrans.transportepublico.webservice.WebService;

public class ViagemServico {

	private Activity _activity;
	private static  ViagemServico _instanciaServico;
	private boolean _viagemIniciada = false;
	
	public ViagemServico(){}
	
	public ViagemServico(Activity activity) {
		_activity = activity;
	}

	public List<LinhaModelo> retornaPesquisa(String filtro)
	{	
		
		String url = _activity.getText((R.string.webservice_pesquisaLinha)).toString();
		url = url.replace("@Linha", filtro);
		
		String json = new WebService(_activity, url).webGet();
		return new LinhaMapeamento().deJson(json);
	}
	
	public void inserirViagem(ViagemModelo viagemModelo)
	{
		new ViagemRepositorio(_activity).inserir(viagemModelo);
	}
	
	public static ViagemServico getInstance()
	{
		if(ViagemServico._instanciaServico == null)
		{
			ViagemServico._instanciaServico = new ViagemServico();
		}
		return ViagemServico._instanciaServico;
	}

	
	public boolean ViagemIniciada() {
		return _viagemIniciada;
	}

	public void ViagemIniciada(boolean _viagemIniciada) {
		this._viagemIniciada = _viagemIniciada;
	}

}
