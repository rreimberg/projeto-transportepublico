package br.sptrans.transportepublico.usuario;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import br.sptrans.transportepublico.listview.FavoritoListView;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.servico.FavoritoServico;

public class FavoritoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_favorito);

		carregaLista();
		
		controleButton(R.tela_favorito.abrir).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int totalLinha = controleListView(R.tela_favorito.lista).getCount();
				List<LinhaModelo> linhaModelos = new ArrayList<LinhaModelo>();
				for (int i = 0; i < totalLinha; i++) {
					View view = controleListView(R.tela_favorito.lista).getChildAt(i);
					
					if(((CheckBox)view.findViewById(R.tela_favorito_listview.checkBox)).isChecked())
						linhaModelos.add((LinhaModelo)controleListView(R.tela_favorito.lista).getItemAtPosition(i));
				}
				
				if(totalLinha != 0)
					abrirAtividadeMapa(linhaModelos);
				else
					mensagem("Selecione ao menos um favorito");
			}
		});
	}
	
	private void carregaLista()
	{
		
		FavoritoServico favoritoServico = new FavoritoServico(getApplicationContext());
		FavoritoListView favoritoListView = new FavoritoListView(getApplicationContext(), favoritoServico.pesquisaFavorito(), R.layout.tela_favorito_listview);
		controleListView(R.tela_favorito.lista).setAdapter(favoritoListView);
		controleListView(R.tela_favorito.lista).setCacheColorHint(Color.TRANSPARENT);
	}
}
