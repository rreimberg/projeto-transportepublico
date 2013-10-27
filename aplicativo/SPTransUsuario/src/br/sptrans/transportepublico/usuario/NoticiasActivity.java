package br.sptrans.transportepublico.usuario;

import java.util.List;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import br.sptrans.transportepublico.listview.NoticiaListView;
import br.sptrans.transportepublico.modelo.NoticiaModelo;
import br.sptrans.transportepublico.servico.NoticiasServico;

public class NoticiasActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tela_noticias);
		
		carregaNoticias();
	}
	
	private void carregaNoticias()
	{
		AsyncTask<Void, Void, List<NoticiaModelo>> asyncTask = new AsyncTask<Void, Void, List<NoticiaModelo>>()
				{
			
			@Override
						protected void onPreExecute() {
							super.onPreExecute();
							mensagem(getText(R.string.geral_atualizando).toString());
						}

		@Override
		protected List<NoticiaModelo> doInBackground(Void... params) {
			return new NoticiasServico(NoticiasActivity.this).pesquisaNoticias();
		}

		@Override
		protected void onPostExecute(List<NoticiaModelo> modelos) {
			super.onPostExecute(modelos);
			NoticiaListView noticiaListView = new NoticiaListView(getApplicationContext(), modelos);
			controleListView(R.tela_noticias.lista).setAdapter(noticiaListView);
			controleListView(R.tela_noticias.lista).setCacheColorHint(Color.TRANSPARENT);	
		}
	 };
	 
	 asyncTask.execute();
		
		
	}
}
