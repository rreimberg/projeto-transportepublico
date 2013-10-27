package br.sptrans.transportepublico.usuario;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.nanoitbrasil.mapa.IMapaServico;
import br.nanoitbrasil.mapa.MapaServico;
import br.nanoitbrasil.mapa.Marcador;
import br.sptrans.transportepublico.identificador.EmpresaIdentificador;
import br.sptrans.transportepublico.identificador.SentidoIdentificador;
import br.sptrans.transportepublico.modelo.CoordenadasModelo;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.modelo.PontoLinhaModelo;
import br.sptrans.transportepublico.modelo.PontoModelo;
import br.sptrans.transportepublico.servico.PontoServico;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class PontosActivity extends BaseFragmentActivity{

	private IMapaServico _mapaServico;
	private CoordenadasModelo coordenadasTela;
	private boolean onibusMaisProximo = false;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.tela_pontos);
		
		SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.tela_pontos.mapa);
		GoogleMap map = mapFragment.getMap();		
		
		_mapaServico = new MapaServico(map,this);
		_mapaServico.habilitaMinhaLocalizacao();
		
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(final Marker marker) {
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(PontosActivity.this);
				dialog.setTitle("O Que deseja fazer?");
				dialog.setPositiveButton("Alertar ônibus mais próximo", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						onibusMaisProximo = true;
						carregaLinhasDoPonto(marker.getTitle(), Integer.parseInt(marker.getSnippet()));
					}
				});
				
				dialog.setNegativeButton("Abrir linhas do ponto",new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						onibusMaisProximo = false;
						carregaLinhasDoPonto(marker.getTitle(), Integer.parseInt(marker.getSnippet()));
					}
				});
				
				dialog.show();
				
				return false;
			}
		});
		
		map.setInfoWindowAdapter(new InfoWindowAdapter() {
			
			@Override
			public View getInfoWindow(Marker arg0) {
				return null;
			}
			
			@Override
			public View getInfoContents(Marker marker) {
				String textoDistancia = "Ponto: %s\nCódigo Parada:%s\nDistância:%s km";
				
				try
				{
					String distancia = retornaDistancia(new CoordenadasModelo(marker.getPosition().latitude, marker.getPosition().longitude), new CoordenadasModelo(latLngUsuario.latitude, latLngUsuario.longitude));
					textoDistancia = String.format(textoDistancia,marker.getTitle(),marker.getSnippet(),distancia);
				}
				catch (Exception e) 
				{
					textoDistancia = String.format(textoDistancia,marker.getTitle(),marker.getSnippet(),"#ERROR");
				}
				
				LinearLayout linearLayout = new LinearLayout(getApplicationContext());
				linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				
				
				TextView textView = new TextView(getBaseContext());
				textView.setText(textoDistancia);
				linearLayout.addView(textView);
				return linearLayout;
			}
		});
		
		controleImageButton(R.tela_pontos.atualizar).setOnClickListener(new  android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				carregaPontos();
			}
		});
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				carregaPontos();
			}
		}, 2000);
	}
	
	private CoordenadasModelo getCoordenadaTela()
	{
		LatLng latLng = _mapaServico.posicaoCamera();
		return new CoordenadasModelo(latLng.latitude, latLng.longitude);
	}
	
	private void carregaPontos()
	{
		AsyncTask<Void, Void, List<PontoModelo>> asyncTask = new AsyncTask<Void,Void,List<PontoModelo>>()
				{
					@Override
					protected void onPreExecute() {
						super.onPreExecute();
						coordenadasTela = getCoordenadaTela();
						Toast.makeText(getApplicationContext(), "Carregando pontos...", Toast.LENGTH_LONG).show();
					}
					
					@Override
					protected List<PontoModelo> doInBackground(Void... params) {
						
						//RadioGroup stopMap_radioGroup_sentido = (RadioGroup)findViewById(R.id.stopMap_radioGroup_sentido);
						//Integer sentido = stopMap_radioGroup_sentido.getCheckedRadioButtonId() == R.id.stopMap_radioGroup_bairro ? 2 : 1;
					
						
						List<PontoModelo> paradas = new PontoServico(PontosActivity.this).pesquisaPontos(
								String.valueOf(coordenadasTela.getLatitude()).replace(".",""), 
								String.valueOf(coordenadasTela.getLongitude()).replace(".",""), 
								SentidoIdentificador.Bairro, 
								1);
						
						return paradas;
					}
					
					@Override
					protected void onPostExecute(List<PontoModelo> pontoModelos) {
						super.onPostExecute(pontoModelos);
						_mapaServico.limpaMapa();
						if(pontoModelos != null)
							for (PontoModelo parada : pontoModelos) {
								_mapaServico.carregaMarcador(new Marcador(
										parada.getName(), 
										String.valueOf(parada.getId()), 
										R.drawable.ponto, 
										parada.getLatitudeDouble(), 
										parada.getLongitudeDouble()));
							}
						
						Toast.makeText(getApplicationContext(), "Pontos atualizados.", Toast.LENGTH_LONG).show();
					}
			
				};
		
		asyncTask.execute();
	}

	private void carregaLinhasDoPonto(final String nomeParada,final int codigoParada) {
		AsyncTask<Void, Void, List<PontoLinhaModelo>> asyncTask = new AsyncTask<Void,Void,List<PontoLinhaModelo>>()
				{
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				Toast.makeText(getApplicationContext(), "Carregando linhas...", Toast.LENGTH_LONG).show();
			}

					@Override
					protected List<PontoLinhaModelo> doInBackground(Void... arg0) {
						return new PontoServico(PontosActivity.this).pesquisaLinhaPorPonto(codigoParada);
					}
					
					@Override
					protected void onPostExecute(final List<PontoLinhaModelo>  paradaLinha) {							
						String[] prefixos = new String[paradaLinha.size()];
						final boolean[] checados = new boolean[paradaLinha.size()];
						
						for (int i = 0; i < paradaLinha.size(); i++) 
						{
							String sentido = paradaLinha.get(i).getTarget();
							prefixos[i] = String.format("%s \n%s",sentido,paradaLinha.get(i).getPrefixAndType());
						}								
						
						AlertDialog.Builder dialog = new AlertDialog.Builder(PontosActivity.this);
						dialog.setTitle(nomeParada);
						dialog.setIcon(R.drawable.ic_launcher);
						dialog.setMultiChoiceItems(prefixos,checados, new OnMultiChoiceClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int id, boolean isChecked) {
								checados[id] = isChecked;		
							}
						});
						dialog.setPositiveButton("Abrir",new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								ArrayList<LinhaModelo> linhaModelos = new ArrayList<LinhaModelo>();
								for (int i = 0; i < checados.length; i++) {
									if(checados[i])
									{
										LinhaModelo linha = new LinhaModelo();
										linha.setCodigoLinha(paradaLinha.get(i).getOlhoVivoId());
										linha.setTipo(paradaLinha.get(i).getPrefixType());
										linha.setLetreiro(paradaLinha.get(i).getPrefix());
										linha.setSentido(paradaLinha.get(i).getDirection());
										linha.setEmpresa(EmpresaIdentificador.SPTrans.getValor());
										linha.setDenominacaoTPTS(paradaLinha.get(i).getTarget());
										linha.setDenominacaoTSTP(paradaLinha.get(i).getTarget());
										linhaModelos.add(linha);
									}
								}
								
								if(linhaModelos.size() != 0)
								{
									if(onibusMaisProximo)
									{
										Intent intent = abrirAtividadeMapaIntent(linhaModelos,new Intent("NotificacaoDistancia"));
										intent.putExtra("pontoId", codigoParada);
										stopService(intent);
										startService(intent);
									}
									else
									{
										Intent i = new Intent(PontosActivity.this,OnibusActivity.class);
										abrirAtividadeMapa(linhaModelos,i);	
									}
								}
								else
									mensagem("Selecione uma ou mais linhas.");
							}
						});
						
						dialog.setNegativeButton("Cancelar", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						});
						dialog.show();
					
					}
				};
				
		asyncTask.execute();
	}


}
