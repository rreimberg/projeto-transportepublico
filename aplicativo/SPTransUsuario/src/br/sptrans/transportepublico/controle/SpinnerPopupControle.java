package br.sptrans.transportepublico.controle;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerPopupControle<TTipoAdapater> extends Builder implements DialogInterface{

	public SpinnerPopupControle(Activity activity,String texto,List<TTipoAdapater> itens) {
		super(activity);
		LinearLayout linearLayout = new LinearLayout(activity);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		
		TextView textView = new TextView(activity);
		textView.setTextColor(Color.BLACK);
		textView.setTextSize(16);
		textView.setText(texto);
		
		Spinner spinner = new Spinner(activity);
		ArrayAdapter<TTipoAdapater> adapter = new ArrayAdapter<TTipoAdapater>(activity, android.R.layout.simple_spinner_item,itens);
		spinner.setAdapter(adapter);
		
		linearLayout.addView(textView);
		linearLayout.addView(spinner);
		
		setView(linearLayout);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		
	}
	
	

}
