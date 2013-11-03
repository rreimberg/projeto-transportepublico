package br.sptrans.transportepublico.painel;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.sptrans.transportepublico.banco.BancoDados;
import br.sptrans.transportepublico.banco.BancoDadosHelper;
import br.sptrans.transportepublico.banco.BancoDadosScript;
import br.sptrans.transportepublico.controle.SimplesDialogoControle;
import br.sptrans.transportepublico.identificador.AlertaIdentificador;

/**
 * Created by luyzfernando on 03/11/2013.
 */
public class Alerta extends BaseActivity {
	
	private Button button_ataque,button_bagunca,button_roubo,button_incendio,
				   button_outros,button_som_alto,button_enviar;
	private EditText edittext_mensagem;
	
	private int ALERTA_SELECIONADO = 0;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_alerta);
        controles();
        selecionaAlerta(R.tela_alerta.button_roubo);
        controloesAcoes();
    }
    

	private void controles()
    {
    	button_ataque = controleButton(R.tela_alerta.button_ataque);
    	button_bagunca = controleButton(R.tela_alerta.button_bagunca);
    	button_roubo = controleButton(R.tela_alerta.button_roubo);
    	button_incendio = controleButton(R.tela_alerta.button_incendio);
    	button_outros = controleButton(R.tela_alerta.button_outros);
    	button_som_alto = controleButton(R.tela_alerta.button_som_alto);
    	edittext_mensagem = controleEditText(R.tela_alerta.edittext_mensagem);
    	button_enviar = controleButton(R.tela_alerta.button_enviar);
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
		});
	}
	
    private void pintaAcoes()
    {
    	for (AlertaIdentificador item : AlertaIdentificador.retornaTudo()) {
			controleButton(item.getControleId()).setBackgroundColor(Color.parseColor(item.getCorId()));
		}
    }
    
    private void selecionaAlerta(int controleId)
    {
    	for (AlertaIdentificador item : AlertaIdentificador.retornaTudo()) {
    		if(controleId != item.getControleId())
    			controleButton(item.getControleId()).setBackgroundColor(Color.GRAY);
    		else
    		{
    			controleButton(item.getControleId()).setBackgroundColor(Color.parseColor(item.getCorId()));
    			ALERTA_SELECIONADO = item.getValor();
    		}
		}
    }
}