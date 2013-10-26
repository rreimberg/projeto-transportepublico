package br.sptrans.transportepublico.usuario;

import android.graphics.Color;
import android.os.Bundle;
import br.sptrans.transportepublico.listview.FavoritoListView;
import br.sptrans.transportepublico.servico.FavoritoServico;

public class FavoritoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_favorito);

		carregaLista();
	}
	
	private void carregaLista()
	{
		FavoritoServico favoritoServico = new FavoritoServico(getApplicationContext());
		FavoritoListView favoritoListView = new FavoritoListView(getApplicationContext(), favoritoServico.pesquisaFavorito(), R.layout.tela_favorito_listview);
		controleListView(R.tela_favorito.lista).setAdapter(favoritoListView);
		controleListView(R.tela_favorito.lista).setCacheColorHint(Color.TRANSPARENT);
	}
}
