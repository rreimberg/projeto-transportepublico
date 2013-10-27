package br.sptrans.transportepublico.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.sptrans.transportepublico.modelo.NoticiaModelo;
import br.sptrans.transportepublico.usuario.R;

public class NoticiaListView extends BaseAdapter{

	private Context _context;
	private List<NoticiaModelo> _modelos;
	public NoticiaListView(Context context,List<NoticiaModelo> modelos) {
		_context = context;
		_modelos = modelos;
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
		//Cria uma instância do layout XML para os objetos correspondentes
        LayoutInflater inflater = (LayoutInflater)
        _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.tela_noticias_listview, null);
        
        TextView texto = (TextView)view.findViewById(R.tela_noticias_listview.texto);
        TextView data = (TextView)view.findViewById(R.tela_noticias_listview.data);
        
    	NoticiaModelo modelo = _modelos.get(posicao);
    	
    	texto.setText(modelo.getText());
    	data.setText(String.valueOf(modelo.getCreated_at()));
        
		return view;
	}
}
