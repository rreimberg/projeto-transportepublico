package br.sptrans.transportepublico.usuario;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import br.sptrans.transportepublico.banco.BancoDados;
import br.sptrans.transportepublico.banco.BancoDadosScript;

public class InicioActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_inicio);
		
		//Cria banco de dados		
		new BancoDados(getApplicationContext(), BancoDadosScript.CreateTables(), "");
				
		controleImageButton(R.tela_inicio.favoritos).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirAtividade(FavoritoActivity.class);
			}
		});
		
		controleImageButton(R.tela_inicio.pesquisa).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirAtividade(PesquisaActivity.class);
			}
		});
		
		controleImageButton(R.tela_inicio.ponto).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirAtividade(PontosActivity.class);
			}
		});	
		
		controleImageButton(R.tela_inicio.noticias).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirAtividade(NoticiasActivity.class);
			}
		});
	}
	
	
}
