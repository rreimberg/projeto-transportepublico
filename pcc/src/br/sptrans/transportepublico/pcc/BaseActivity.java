package br.sptrans.transportepublico.usuario;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import br.sptrans.transportepublico.modelo.LinhaModelo;

public class BaseActivity extends Activity{

	public void mensagem(String text)
	{
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
	}
	
	public ImageButton controleImageButton(int id)
	{
		return (ImageButton)findViewById(id);
	}
	
	public Button controleButton(int id)
	{
		return (Button)findViewById(id);
	}
	
	public EditText controleEditText(int id)
	{
		return (EditText)findViewById(id);
	}
	
	public ListView controleListView(int id)
	{
		return (ListView)findViewById(id);
	}
	
	public CheckBox controleCheckBox(int id)
	{
		return (CheckBox)findViewById(id);
	}

	public void abrirAtividade(@SuppressWarnings("rawtypes") Class tela)
	{
		startActivity(new Intent(getApplicationContext(),tela));
	}
	public void async(final Runnable runnable)
	{
		AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>()
				{

					@Override
					protected Void doInBackground(Void... params) {
						runnable.run();
						return null;
					}
			
				};
				
				asyncTask.execute();
	}
	
	public void abrirAtividadeMapa(LinhaModelo linhaModelo)
	{
		List<LinhaModelo> linhaModelos = new ArrayList<LinhaModelo>();
		linhaModelos.add(linhaModelo);
		abrirAtividadeMapa(linhaModelos);
	}
	
	public void abrirAtividadeMapa(List<LinhaModelo> linhaModelos)
	{
		String linhaIds = "";
		String prefixos = "";
		String tipos = "";
		String sentidos = "";
		String empresas = "";
		String denominacaoTPTS = "";
		String denominacaoTSTP = "";
		
		for (LinhaModelo linha : linhaModelos) 
		{
			linhaIds += linha.getCodigoLinha() + ",";
			prefixos += linha.getLetreiro() + ",";
			sentidos += linha.getSentido() + ",";
			empresas += linha.getEmpresa() + ",";
			denominacaoTPTS += linha.getDenominacaoTPTS() + ",";
			denominacaoTSTP += linha.getDenominacaoTSTP() + ",";
			tipos += linha.getTipo() + ",";
		}
		
		Intent i = new Intent(getApplicationContext(),OnibusActivity.class);
		//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("linhaId",linhaIds);
		i.putExtra("prefixos",prefixos);
		i.putExtra("tipos",tipos);
		i.putExtra("sentidos",sentidos);
		i.putExtra("empresas", empresas);
		i.putExtra("denominacaoTPTS", denominacaoTPTS);
		i.putExtra("denominacaoTSTP", denominacaoTSTP);
		startActivity(i);
	}
}
