package br.sptrans.transportepublico.painel;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import br.sptrans.transportepublico.controle.SimplesDialogoControle;
import br.sptrans.transportepublico.identificador.MenuIdentificador;
import br.sptrans.transportepublico.identificador.TransitoIdentificador;

/**
 * Created by rreimberg on 10/31/13.
 */
public class Transito extends BaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_transito);
        menuSelecionado(MenuIdentificador.Transito);
        
        for (final TransitoIdentificador item : TransitoIdentificador.retornaTudo()) {
			((ImageButton)findViewById(item.getControleId())).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for (TransitoIdentificador transitoIdentificador : TransitoIdentificador.retornaTudo()) {
						if(item.getControleId() != transitoIdentificador.getControleId())
							((ImageButton)findViewById(transitoIdentificador.getControleId())).setBackgroundColor(Color.GRAY);
						else
							((ImageButton)findViewById(transitoIdentificador.getControleId())).setBackgroundColor(Color.parseColor(transitoIdentificador.getCorId()));
					}
					
					SimplesDialogoControle simplesDialogoControle = new SimplesDialogoControle(Transito.this, "Trânsito", "Confirma um envio de alerta de trânsito?");
					simplesDialogoControle.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener(
							) {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							mensagem("Informação enviada com sucesso.");
						}
					}); 
					simplesDialogoControle.show();
				}
			});
		}
    }
}