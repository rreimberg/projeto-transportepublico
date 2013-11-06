package br.sptrans.transportepublico.painel;

import br.sptrans.transportepublico.identificador.MenuIdentificador;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
	
	public TextView controleTextView(int id)
	{
		return (TextView)findViewById(id);
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
	
	protected void esconderTeclado()
	{
		try
		{
			InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
			inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}
		catch (Exception e) {

		}
	}
	
	protected void menuSelecionado(MenuIdentificador viagem)
    {
    	for (MenuIdentificador item : MenuIdentificador.retornaTudo()) {
			if(viagem.getControleId() != item.getControleId())
				((ImageButton)findViewById(item.getControleId())).setBackgroundColor(Color.GRAY);
			else
				((ImageButton)findViewById(item.getControleId())).setBackgroundColor(Color.parseColor(item.getCorId()));
		}
    }
	
	@Override
    public void onBackPressed() {
        //mensagem("Não é possível sair da aplicação.");
    }
}
