package br.sptrans.transportepublico.mapeamento;

import br.sptrans.transportepublico.modelo.FavoritoModelo;
import br.sptrans.transportepublico.modelo.PesquisaModelo;

public class FavoritoMapeamento {

	public FavoritoModelo dePesquisaModeloParaFavoritoModelo(PesquisaModelo pesquisaModelo)
	{
		FavoritoModelo favoritoModelo = new FavoritoModelo();
		favoritoModelo.setCircular(pesquisaModelo.getCircular());
		favoritoModelo.setCodigoLinha(pesquisaModelo.getCodigoLinha());
		favoritoModelo.setDenominacaoTPTS(pesquisaModelo.getDenominacaoTPTS());
		favoritoModelo.setDenominacaoTSTP(pesquisaModelo.getDenominacaoTSTP());
		favoritoModelo.setEmpresa(pesquisaModelo.getEmpresa());
		favoritoModelo.setLetreiro(pesquisaModelo.getLetreiro());
		favoritoModelo.setSentido(pesquisaModelo.getSentido());
		favoritoModelo.setTipo(pesquisaModelo.getTipo());
		
		return favoritoModelo;
	}
}
