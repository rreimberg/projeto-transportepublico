package br.sptrans.transportepublico.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.sptrans.transportepublico.modelo.FavoritoModelo;
import br.sptrans.transportepublico.usuario.R;

public class FavoritoListView extends BaseAdapter{

	private Context _context;
	private List<FavoritoModelo> _modelos;
	private int _view;
	public FavoritoListView(Context context,List<FavoritoModelo> modelos,int view) {
		_context = context;
		_modelos = modelos;
		_view = view;
	}
	
	@Override
	public int getCount() {
		return _modelos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return _modelos.get(arg0);
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
        
        TextView linhaDestino = (TextView)view.findViewById(R.tela_favorito_listview.destino);
        TextView linhaOrigem = (TextView)view.findViewById(R.tela_favorito_listview.origem);
        TextView letreiro = (TextView)view.findViewById(R.tela_favorito_listview.letreiro);
        
    	FavoritoModelo favoritoModelo = _modelos.get(posicao);
    	
    	linhaDestino.setText("Dest.: " + (favoritoModelo.GetDestino().length() > 25 ? favoritoModelo.GetDestino().substring(0, 25) + "...": favoritoModelo.GetDestino()));
        linhaOrigem.setText("Orig.: " + (favoritoModelo.GetOrigem().length() > 25 ? favoritoModelo.GetOrigem().substring(0, 25) + "...": favoritoModelo.GetOrigem()));
        letreiro.setText("Prefixo:" + favoritoModelo.GetLetreiroTipo());
        
		return view;
	}
}
