package br.sptrans.transportepublico.painel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import br.sptrans.transportepublico.banco.BancoDados;
import br.sptrans.transportepublico.banco.BancoDadosScript;
import br.sptrans.transportepublico.controle.AlertDialogListControle;
import br.sptrans.transportepublico.controle.LinhaDialogoControle;
import br.sptrans.transportepublico.controle.SimplesDialogoControle;
import br.sptrans.transportepublico.identificador.MenuIdentificador;
import br.sptrans.transportepublico.identificador.OnibusAbortoIdentificador;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.modelo.ViagemModelo;
import br.sptrans.transportepublico.servico.ViagemServico;

/**
 * Created by rreimberg on 10/31/13.
 */

public class Viagem extends BaseActivity {

	private ImageButton imagebutton_pesquisar = null;

	private Button imagebutton_iniciar_viagem,imagebutton_abortar_viagem,imagebutton_finalizar_viagem = null;
	private EditText edittext_codigo_linha,edittext_contador,edittext_prefixo_linha_valor,edittext_contador_fim = null;
	private TextView textview_origem_valor,textview_destino_valor,textview_inicio_valor = null;
	
	private List<LinhaModelo> _linhaModelos = null;	
	private int LINHA_CODIGO_SELECIONADA = 0;
	private boolean VIAGEM_INICIADA = false; 

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_viagem);

        //deleteDatabase("painel.db");
        new BancoDados(getApplicationContext(), BancoDadosScript.CreateTables(), null);

        controles();
        controlesAcoes();
        esconderTeclado();
        
        imagebutton_abortar_viagem.setVisibility(View.GONE);
        imagebutton_finalizar_viagem.setVisibility(View.GONE);
        edittext_contador_fim.setEnabled(false);
        edittext_prefixo_linha_valor.requestFocus();
        menuSelecionado(MenuIdentificador.Viagem);
    }

    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	esconderTeclado();		
    }
    
    private void controles()
    {
    	imagebutton_pesquisar = controleImageButton(R.tela_viagem.imagebutton_pesquisar);
    	imagebutton_iniciar_viagem = controleButton(R.tela_viagem.imagebutton_iniciar_viagem);
    	imagebutton_abortar_viagem = controleButton(R.tela_viagem.imagebutton_abortar_viagem);
    	imagebutton_finalizar_viagem = controleButton(R.tela_viagem.imagebutton_finalizar_viagem);
    	edittext_codigo_linha = controleEditText(R.tela_viagem.edittext_codigo_linha);
    	textview_origem_valor = controleTextView(R.tela_viagem.textview_origem_valor);
    	textview_destino_valor = controleTextView(R.tela_viagem.textview_destino_valor);
    	edittext_contador = controleEditText(R.tela_viagem.edittext_contador);
    	edittext_contador_fim = controleEditText(R.tela_viagem.edittext_contador_fim);
    	textview_inicio_valor = controleTextView(R.tela_viagem.textview_inicio_valor);
    	edittext_prefixo_linha_valor = controleEditText(R.tela_viagem.edittext_prefixo_linha_valor);
    }

    private void controlesAcoes()
    {
    	imagebutton_pesquisar.setOnClickListener(pesquisarOnClick());
    	imagebutton_iniciar_viagem.setOnClickListener(iniciarViagemOnClick());
    	imagebutton_finalizar_viagem.setOnClickListener(pararViagemOnClick());
    	imagebutton_abortar_viagem.setOnClickListener(abortarViagemOnClick());
    }

    private OnClickListener abortarViagemOnClick() {
		return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialogListControle<OnibusAbortoIdentificador>(Viagem.this, "Motivo da abortagem:", 
						OnibusAbortoIdentificador.retornaTudo(),new android.content.DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int posicao) {
								pararViagem();
							}
						}).show();
			}
		};
	}

	private OnClickListener pesquisarOnClick()
    {
    	return new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (edittext_codigo_linha.length() == 0)
				{
					mensagem("Digite uma linha.");
					edittext_codigo_linha.requestFocus();
					return;
				}

				esconderTeclado();
				AsyncTask<Void, Void, List<LinhaModelo>> asyncTask = new AsyncTask<Void, Void, List<LinhaModelo>>(){

					@Override
					protected void onPreExecute() {
						super.onPreExecute();
						mensagem("Pesquisando...");
					}
					@Override
					protected List<LinhaModelo> doInBackground(Void... params) {
						_linhaModelos = new ViagemServico(Viagem.this).retornaPesquisa(edittext_codigo_linha.getText().toString());

						return _linhaModelos;
					}

					@Override
					protected void onPostExecute(List<LinhaModelo> result) {
						super.onPostExecute(result);
						if(result == null || result.size() == 0)
						{
							mensagem("Nenhuma linha encontrada");
							return;
						}
						
						LinhaDialogoControle linhaDialogoControle = new LinhaDialogoControle(
								Viagem.this,
								"Defina uma linha:",
								result,
								new android.content.DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog, int posicao) {
										selecionaViagem(_linhaModelos.get(posicao));
									}
								} );
						linhaDialogoControle.show();
					}
				};

				asyncTask.execute();
			}
		};
    }

    private OnClickListener iniciarViagemOnClick()
    {
    	return new OnClickListener() {

			@Override
			public void onClick(View v) {

				iniciarViagem();
			}			
		};
    }
    
    private OnClickListener pararViagemOnClick()
    {
    	return new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pararViagem();
			}
		};
    }
 
    private void selecionaViagem(LinhaModelo linhaModelo)
    {
    	LINHA_CODIGO_SELECIONADA = Integer.parseInt(linhaModelo.getCodigoLinha());
    	textview_destino_valor.setText(linhaModelo.getDestino());
    	textview_origem_valor.setText(linhaModelo.getOrigem());
    }
    
    private void pararViagem() {
		SimplesDialogoControle simplesDialogoControle = new SimplesDialogoControle
				(this, "Confirmação", "Deseja realmente finalizar a viagem?");
		simplesDialogoControle.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(!validaFimViagem())
					return;
				
				imagebutton_iniciar_viagem.setBackgroundColor(Color.rgb(0, 128, 0));
				imagebutton_iniciar_viagem.setText("Iniciar Viagem");

				LINHA_CODIGO_SELECIONADA = 0;
				textview_destino_valor.setText(null);
		    	textview_origem_valor.setText(null);
		    	edittext_contador.setText(null);
		    	edittext_contador_fim.setText(null);
		    	textview_inicio_valor.setText(null);
				VIAGEM_INICIADA = false;
				
				imagebutton_iniciar_viagem.setVisibility(View.VISIBLE);
				imagebutton_finalizar_viagem.setVisibility(View.GONE);
				imagebutton_abortar_viagem.setVisibility(View.GONE);
				edittext_contador.setEnabled(true);
				edittext_contador_fim.setEnabled(false);
				ViagemServico.getInstance().ViagemIniciada(false);
				//bloquearMenus(true);
			}
		});
		
		simplesDialogoControle.setNegativeButton("Não", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		simplesDialogoControle.show();
	}

	private void iniciarViagem() {		
		if(!validaInicioViagem())
			return;

		SimplesDialogoControle simplesDialogoControle = new SimplesDialogoControle
				(this, "Confirmação", "Todas as informações estão corretas?" +
						"\nDeseja realmente iniciar a viagem?");
		simplesDialogoControle.setPositiveButton("Sim", new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				textview_inicio_valor.setText(DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()).toString());
				imagebutton_iniciar_viagem.setBackgroundColor(Color.RED);
				imagebutton_iniciar_viagem.setText("Finalizar Viagem");
				VIAGEM_INICIADA = true;
				
				imagebutton_iniciar_viagem.setVisibility(View.GONE);
				imagebutton_finalizar_viagem.setVisibility(View.VISIBLE);
				imagebutton_abortar_viagem.setVisibility(View.VISIBLE);
				edittext_contador_fim.setEnabled(true);
				edittext_contador.setEnabled(false);
				ViagemServico.getInstance().ViagemIniciada(true);
				new ViagemServico(Viagem.this).inserirViagem(new ViagemModelo(0, UUID.randomUUID().toString(), LINHA_CODIGO_SELECIONADA,
						Integer.parseInt(edittext_prefixo_linha_valor.getText().toString()),0, 0, null));
				
				//bloquearMenus(false);
			}
		});
		
		simplesDialogoControle.setNegativeButton("Não", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		simplesDialogoControle.show();
	}
	
	private boolean validaInicioViagem()
	{
		boolean valido = true;
		StringBuilder mensagens = new StringBuilder();
		if(LINHA_CODIGO_SELECIONADA == 0)
		{
			mensagens.append("Selecione um destino válido.\n");
			edittext_codigo_linha.requestFocus();
			valido = false;
		}

		if(edittext_contador.length() < 6)
		{
			mensagens.append("Informe os seis digitos da catraca.\n");
			edittext_contador.requestFocus();
			valido = false;
		}
		
		if(edittext_prefixo_linha_valor.length() < 5)
		{
			mensagens.append("Prefixo do ônibus incorreto.\n");
			edittext_contador.requestFocus();
			valido = false;
		}
		
		if(edittext_prefixo_linha_valor.getText() == null || edittext_prefixo_linha_valor.length() == 0)
		{
			mensagens.append("Informe o prefixo do veículo\n");
			edittext_prefixo_linha_valor.requestFocus();
			valido = false;
		}
		
		if(!valido)
			mensagem(mensagens.toString());

		return valido;
	}
	
	private boolean validaFimViagem()
	{
		boolean valido = true;
		StringBuilder mensagens = new StringBuilder();
		if(edittext_contador_fim.length() < 6)
		{
			mensagens.append("Informe os seis digitos da catraca.");
			edittext_contador_fim.requestFocus();
			valido = false;
		}
		
		if (Integer.parseInt(edittext_contador_fim.getText().toString()) < Integer.parseInt(edittext_contador.getText().toString()))
		{
			mensagens.append("Contador da catraca inválido.");
			edittext_contador_fim.requestFocus();
			valido = false;
		}
		if(!valido)
			mensagem(mensagens.toString());

		return valido;
	}

	protected void bloquearMenus2(boolean valor)
    {
    	for (MenuIdentificador item : MenuIdentificador.retornaTudo()) {
			if(MenuIdentificador.Viagem.getControleId() != item.getControleId())
				((ImageButton)findViewById(item.getControleId())).setEnabled(!valor);
		}
    }
}
