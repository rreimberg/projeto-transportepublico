package br.sptrans.transportepublico.painel;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import br.sptrans.transportepublico.controle.SimplesDialogoControle;
import br.sptrans.transportepublico.identificador.AlertaIdentificador;

/**
 * Created by luyzfernando on 03/11/2013.
 */
public class Alerta extends BaseActivity {
	
	private ImageButton button_ataque,button_bagunca,button_roubo,button_incendio,
				   button_outros,button_som_alto;
	
	private int ALERTA_SELECIONADO = 0;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_alerta);
        controles();
        controloesAcoes();
    }
    

	private void controles()
    {
    	button_ataque = controleImageButton(R.tela_alerta.button_ataque);
    	button_bagunca = controleImageButton(R.tela_alerta.button_bagunca);
    	button_roubo = controleImageButton(R.tela_alerta.button_roubo);
    	button_incendio = controleImageButton(R.tela_alerta.button_incendio);
    	button_outros = controleImageButton(R.tela_alerta.button_outros);
    	button_som_alto = controleImageButton(R.tela_alerta.button_som_alto);
    }

	private void controloesAcoes() {
		button_roubo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selecionaAlerta(R.tela_alerta.button_roubo);
			}
		});
		
		button_ataque.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selecionaAlerta(R.tela_alerta.button_ataque);
			}
		});
		
		button_bagunca.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selecionaAlerta(R.tela_alerta.button_bagunca);
			}
		});

		button_incendio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selecionaAlerta(R.tela_alerta.button_incendio);
			}
		});
		
		button_outros.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selecionaAlerta(R.tela_alerta.button_outros);
			}
		});
		
		button_som_alto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				selecionaAlerta(R.tela_alerta.button_som_alto);
			}
		});
	
		/*
		button_enviar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SimplesDialogoControle simplesDialogoControle = new SimplesDialogoControle
						(Alerta.this, "Confirmação", "Confirma o envio do alerta?");
				
				simplesDialogoControle.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mensagem(AlertaIdentificador.retornaPorValor(ALERTA_SELECIONADO).getNome());
					}
				});
				
				simplesDialogoControle.setNegativeButton("Não", new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mensagem("Ação cancelada");
						dialog.dismiss();
					}
				});
				
				simplesDialogoControle.show();
			}
		});*/
	}
	
    private void pintaAcoes()
    {
    	for (AlertaIdentificador item : AlertaIdentificador.retornaTudo()) {
    		controleImageButton(item.getControleId()).setBackgroundColor(Color.parseColor(item.getCorId()));
		}
    }
    
    private void selecionaAlerta(int controleId)
    {
    	for (AlertaIdentificador item : AlertaIdentificador.retornaTudo()) {
    		if(controleId != item.getControleId())
    			controleImageButton(item.getControleId()).setBackgroundColor(Color.GRAY);
    		else 
    		{
    			controleImageButton(item.getControleId()).setBackgroundColor(Color.parseColor(item.getCorId()));
    			ALERTA_SELECIONADO = item.getValor();
    		}
		}
    	
    	SimplesDialogoControle simplesDialogoControle = new SimplesDialogoControle
				(Alerta.this, "Confirmação", String.format("Confirma o envio do alerta: %s?",AlertaIdentificador.retornaPorValor(ALERTA_SELECIONADO).getNome()));
		
		simplesDialogoControle.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mensagem(AlertaIdentificador.retornaPorValor(ALERTA_SELECIONADO).getNome());
				
				
			}
		});
		
		simplesDialogoControle.setNegativeButton("Não", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mensagem("Ação cancelada");
				dialog.dismiss();
			}
		});
		
		simplesDialogoControle.show();
    }
}