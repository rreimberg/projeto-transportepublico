package br.sptrans.transportepublico.controle;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import br.sptrans.transportepublico.painel.R;

public class SimplesDialogoControle extends Builder implements DialogInterface{
	
	public SimplesDialogoControle(Activity activity,String titulo,String descricao) {
		super(activity);
	
		setTitle(titulo);
		setMessage(descricao);
		setIcon(R.drawable.ic_launcher);
	}

	@Override
	public void cancel() {
		cancel();
		
	}

	@Override
	public void dismiss() {
		dismiss();
	}
}
