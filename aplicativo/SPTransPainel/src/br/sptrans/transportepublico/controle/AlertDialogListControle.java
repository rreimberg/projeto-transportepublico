package br.sptrans.transportepublico.controle;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import br.sptrans.transportepublico.identificador.BaseIdentificador;
import br.sptrans.transportepublico.painel.R;

public class AlertDialogListControle<TTipoAdapater> extends Builder implements DialogInterface{

	
	public List<TTipoAdapater> _itens = null;
	@SuppressWarnings("unchecked")
	public AlertDialogListControle(Activity activity,String titulo,List<TTipoAdapater> itens,
			android.content.DialogInterface.OnClickListener clickListener) {
		super(activity);
	
		_itens = itens;
		setTitle(titulo);
		setIcon(R.drawable.ic_launcher);
		
		String[] base = new String[itens.size()];
		for (int i = 0; i < itens.size(); i++) {
			base[i] = ((BaseIdentificador<TTipoAdapater>)itens.get(i)).getNome();
		}
		
		setItems(base, clickListener);
	}

	@Override
	public void cancel() {
		
	}

	@Override
	public void dismiss() {
		
	}
	
	@SuppressWarnings("unchecked")
	public int getValorSelecionado(int posicao)
	{
		return (Integer) ((BaseIdentificador<TTipoAdapater>)_itens.get(posicao)).getValor();
	}
}
