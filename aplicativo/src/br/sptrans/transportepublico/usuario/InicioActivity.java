package br.sptrans.transportepublico.usuario;

import br.sptrans.transportepublico.banco.BancoDados;
import br.sptrans.transportepublico.banco.BancoDadosScript;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class InicioActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_inicio);
		
		//Cria banco de dados		
		new BancoDados(getApplicationContext(), BancoDadosScript.CreateTables(), "");
				
		controleButton(R.tela_inicio.favorito).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirAtividade(FavoritoActivity.class);
			}
		});
		
		controleButton(R.tela_inicio.pesquisa).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirAtividade(PesquisaActivity.class);
			}
		});
	}
}
