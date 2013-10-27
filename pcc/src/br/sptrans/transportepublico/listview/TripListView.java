package br.sptrans.transportepublico.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import br.sptrans.transportepublico.modelo.PesquisaModelo;
import br.sptrans.transportepublico.pcc.R;

public class TripListView extends BaseAdapter{

	private Context _context;
	private List<PesquisaModelo> _pesquisaModelos;
	private int _view;
	public TripListView(Context context, List<PesquisaModelo> pesquisaModelos, int view) {
		_context = context;
		_pesquisaModelos = pesquisaModelos;
		_view = view;
	}
	
	@Override
	public int getCount() {
		return _pesquisaModelos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return _pesquisaModelos.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int posicao, View arg1, ViewGroup arg2) {
		// Cria uma instância do layout XML para os objetos correspondentes
        LayoutInflater inflater = (LayoutInflater)
        _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(_view, null);

    	PesquisaModelo pesquisaModelo = _pesquisaModelos.get(posicao);
        String text = pesquisaModelo.GetLetreiroTipo() + " -> " + pesquisaModelo.GetDestino();

        Button select = (Button) view.findViewById(R.trips.item);
        select.setText(text);
        select.setFocusable(false);
        select.setOnClickListener(clickTrip(pesquisaModelo));
		return view;
	}

    private OnClickListener clickTrip(final PesquisaModelo pesquisaModelo) {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = pesquisaModelo.GetLetreiroTipo() + ": " + pesquisaModelo.GetOrigem() + " --> " + pesquisaModelo.GetDestino();
               // _context.this.findById(R.journey.trip_headsign).setText(text);
               // _context.trip = pesquisaModelo;
            }
        };
    }
}
