package br.sptrans.transportepublico.controle;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.painel.R;

public class LinhaDialogoControle extends Builder implements DialogInterface{

	
	private List<LinhaModelo> _itens = null;
	
	public LinhaDialogoControle(Activity activity,String titulo,
			List<LinhaModelo> itens,
			android.content.DialogInterface.OnClickListener clickListener) {
		super(activity);
	
		_itens = itens;
		setTitle(titulo);
		setIcon(R.drawable.ic_launcher);
			
		String[] base = new String[itens.size()];
		
		for (int i = 0; i < itens.size(); i++) {
			base[i] = _itens.get(i).getLetreiroTipo() + " :: " + _itens.get(i).getDestino();
		}
		setItems(base, clickListener);
		
	}

	@Override
	public void cancel() {
		
	}

	@Override
	public void dismiss() {
		
	}
	
	public String getValorSelecionado(int posicao)
	{
		return _itens.get(posicao).getCodigoLinha();
	}
}
