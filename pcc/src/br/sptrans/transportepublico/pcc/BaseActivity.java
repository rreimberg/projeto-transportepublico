package br.sptrans.transportepublico.pcc;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
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

    protected void defaultNavigation()
    {
        findViewById(R.navigation_menu.journey).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirAtividade(JourneyActivity.class);
            }
        });

        findViewById(R.navigation_menu.traffic).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirAtividade(TrafficActivity.class);
            }
        });

        findViewById(R.navigation_menu.stocking).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirAtividade(StockingActivity.class);
            }
        });

        findViewById(R.navigation_menu.alert).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirAtividade(AlertActivity.class);
            }
        });
    }
}
