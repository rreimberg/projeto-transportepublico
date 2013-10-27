package br.sptrans.transportepublico.pcc;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import br.sptrans.transportepublico.listview.TripListView;
import br.sptrans.transportepublico.mapeamento.PesquisaMapeamento;
import br.sptrans.transportepublico.modelo.PesquisaModelo;
import br.sptrans.transportepublico.webservice.WebService;

/**
 * Created by rreimberg on 10/26/13.
 */
public class JourneyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        defaultNavigation();

        findViewById(R.journey.search_trip).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                esconderTeclado();
                carregaLista();
            }
        });

    }

    public void carregaLista()
    {
        JourneyActivity.this.findViewById(R.journey.trips).setVisibility(View.VISIBLE);

        /*AsyncTask<Void, Void, List<PesquisaModelo>> asyncTask = new AsyncTask<Void, Void, List<PesquisaModelo>>()
        {

            @Override
            protected List<PesquisaModelo> doInBackground(Void... params) {

                String url = JourneyActivity.this.getText((R.string.webservice_pesquisaLinha)).toString();
                url = url.replace("@Linha", controleEditText(R.journey.route_id).getText().toString());
                url = url.replace("@ApplicationId", "1");
                url = url.replace("@DeviceId", "1");

                String json = new WebService(JourneyActivity.this, url).webGet();
                return new PesquisaMapeamento().deJsonParaModelo(json);

            }

            @Override
            protected void onPostExecute(List<PesquisaModelo> pesquisaModelos) {
                super.onPostExecute(pesquisaModelos);
               // TripListView items = new TripListView(JourneyActivity.this, pesquisaModelos, R.layout.item_trip);
               // ListView trips = (ListView) JourneyActivity.this.findViewById(R.journey.trips);
               // trips.setAdapter(items);
               // trips.setVisibility(View.VISIBLE);
            }
        };

        asyncTask.execute();*/
    }

    public void esconderTeclado()
    {
        try
        {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (Exception e) {

        }
    }
}
