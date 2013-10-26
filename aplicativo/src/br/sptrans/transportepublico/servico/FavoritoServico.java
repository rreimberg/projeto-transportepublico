package br.sptrans.transportepublico.servico;

import java.util.List;

import android.content.Context;
import br.sptrans.transportepublico.modelo.FavoritoModelo;
import br.sptrans.transportepublico.repositorio.FavoritoRepositorio;

public class FavoritoServico {

	private Context _Context;
	public FavoritoServico(Context applicationContext) {
		_Context = applicationContext;
	}

	public List<FavoritoModelo> pesquisaFavorito()
	{
		FavoritoRepositorio favoritoRepositorio = new FavoritoRepositorio(_Context);
		return favoritoRepositorio.pesquisarTudo();
	}
}
