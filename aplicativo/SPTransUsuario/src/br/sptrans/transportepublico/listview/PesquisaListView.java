package br.sptrans.transportepublico.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import br.sptrans.transportepublico.mapeamento.FavoritoMapeamento;
import br.sptrans.transportepublico.modelo.FavoritoModelo;
import br.sptrans.transportepublico.modelo.PesquisaModelo;
import br.sptrans.transportepublico.repositorio.FavoritoRepositorio;
import br.sptrans.transportepublico.usuario.R;

public class PesquisaListView extends BaseAdapter{

	private Context _context;
	private List<PesquisaModelo> _pesquisaModelos;
	private int _view;
	public PesquisaListView(Context context,List<PesquisaModelo> pesquisaModelos,int view) {
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
        
        TextView linhaDestino = (TextView)view.findViewById(R.tela_pesquisa_listview.destino);
        TextView linhaOrigem = (TextView)view.findViewById(R.tela_pesquisa_listview.origem);
        TextView letreiro = (TextView)view.findViewById(R.tela_pesquisa_listview.letreiro);
        
    	PesquisaModelo pesquisaModelo = _pesquisaModelos.get(posicao);
    	
    	linhaDestino.setText("Dest.: " + (pesquisaModelo.GetDestino().length() > 25 ? pesquisaModelo.GetDestino().substring(0, 25) + "...": pesquisaModelo.GetDestino()));
        linhaOrigem.setText("Orig.: " + (pesquisaModelo.GetOrigem().length() > 25 ? pesquisaModelo.GetOrigem().substring(0, 25) + "...": pesquisaModelo.GetOrigem()));
        letreiro.setText("Prefixo:" + pesquisaModelo.GetLetreiroTipo());
        
        //ImageView img = (ImageView)view.findViewById(R.tela_pesquisa_listview.onibus);
        //img.setImageResource(pesquisaModelos.GetAvatar());
        //ImageView listviewcustom_imageview_logoempresa = (ImageView)view.findViewById(R.tela_pesquisa_listview.logo);
        //listviewcustom_imageview_logoempresa.setFocusable(false);
        //listviewcustom_imageview_logoempresa.setImageResource(linhaSptrans.GetIconeEmpresa());
 
        ImageButton favorito = (ImageButton)view.findViewById(R.tela_pesquisa_listview.favorito);
        favorito.setFocusable(false);
        favorito.setOnClickListener(clickFavorito(pesquisaModelo));
		return view;
	}

	private OnClickListener clickFavorito(final PesquisaModelo pesquisaModelo) {
		return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FavoritoModelo f = new FavoritoMapeamento().dePesquisaModeloParaFavoritoModelo(pesquisaModelo);
								
				FavoritoRepositorio favoritoRepositorio = new FavoritoRepositorio(_context);
				FavoritoModelo result = favoritoRepositorio.pesquisaPorId(pesquisaModelo.getCodigoLinha());
				
				if(result == null)
				{
					favoritoRepositorio.inserir(f);
					Toast.makeText(_context, "Favorito adicionado.", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(_context, "Favorito já existe.", Toast.LENGTH_LONG).show();
			}
		};
	}
}
