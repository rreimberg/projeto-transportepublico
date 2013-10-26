package br.sptrans.transportepublico.usuario;

import java.util.List;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import br.sptrans.transportepublico.listview.PesquisaListView;
import br.sptrans.transportepublico.modelo.PesquisaModelo;
import br.sptrans.transportepublico.servico.PesquisaServico;

public class PesquisaActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_pesquisa);
		
		ImageButton b = controleImageButton(R.tela_pesquisa.pesquisar);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				carregaLista();
			}
		});
	}
	
	public void carregaLista()
	{
		AsyncTask<Void, Void, List<PesquisaModelo>> asyncTask = new AsyncTask<Void, Void, List<PesquisaModelo>>()
				{

		@Override
		protected List<PesquisaModelo> doInBackground(Void... params) {
			return new PesquisaServico(PesquisaActivity.this).retornaPesquisa(controleEditText(R.tela_pesquisa.busca).getText().toString());
		}

		@Override
		protected void onPostExecute(List<PesquisaModelo> pesquisaModelos) {
			super.onPostExecute(pesquisaModelos);
			PesquisaListView pesquisaListView = new PesquisaListView(PesquisaActivity.this, pesquisaModelos,R.layout.tela_pesquisa_listview);
			controleListView(R.tela_pesquisa.lista).setAdapter(pesquisaListView);
			controleListView(R.tela_pesquisa.lista).setCacheColorHint(Color.TRANSPARENT);			
		}
	 };
	 
	 asyncTask.execute();
	}
	
}