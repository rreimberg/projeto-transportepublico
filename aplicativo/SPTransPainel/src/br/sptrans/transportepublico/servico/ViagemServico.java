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
	
	public ViagemServico(Activity activity) {
		_activity = activity;
	}

	public List<LinhaModelo> retornaPesquisa(String filtro)
	{	
		
		String url = _activity.getText((R.string.webservice_pesquisaLinha)).toString();
		url = url.replace("@Linha", filtro);
		
		String json = new WebService(_activity, url).webGet();
		return new LinhaMapeamento().deJson(json);
		/*
		LinhaModelo linhaModelo = new LinhaModelo();
		linhaModelo.setCodigoLinha("1196");
		linhaModelo.setDenominacaoTPTS("Herplin");
		linhaModelo.setDenominacaoTSTP("Praça");
		linhaModelo.setSentido(2);
		linhaModelo.setTipo("10");
		linhaModelo.setEmpresa(1);
		List<LinhaModelo> linhaModelos = new ArrayList<LinhaModelo>();
		linhaModelos.add(linhaModelo);
		return linhaModelos;*/
	}
	
	public void inserirViagem(ViagemModelo viagemModelo)
	{
		new ViagemRepositorio(_activity).inserir(viagemModelo);
	}
}
