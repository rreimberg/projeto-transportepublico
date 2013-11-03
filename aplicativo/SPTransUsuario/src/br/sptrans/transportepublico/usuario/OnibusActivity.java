package br.sptrans.transportepublico.usuario;

import java.util.ArrayList;
import java.util.List;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.nanoitbrasil.mapa.IMapaServico;
import br.nanoitbrasil.mapa.MapaServico;
import br.nanoitbrasil.mapa.Marcador;
import br.sptrans.transportepublico.controle.AlertDialogListControle;
import br.sptrans.transportepublico.identificador.EmpresaIdentificador;
import br.sptrans.transportepublico.identificador.OnibusAvaliacaoIdentificador;
import br.sptrans.transportepublico.identificador.OnibusLotacaoIdentificador;
import br.sptrans.transportepublico.identificador.OnibusTipoIdentificador;
import br.sptrans.transportepublico.identificador.SentidoIdentificador;
import br.sptrans.transportepublico.identificador.TransitoIdentificador;
import br.sptrans.transportepublico.modelo.CoordenadasModelo;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.modelo.OnibusModelo;
import br.sptrans.transportepublico.servico.ComumServico;
import br.sptrans.transportepublico.servico.OnibusServico;
import br.sptrans.transportepublico.servico.RotaServico;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class OnibusActivity extends BaseFragmentActivity {

	List<LinhaModelo> _linhaModelos = new ArrayList<LinhaModelo>();
	private IMapaServico _mapaServico;
	private String PREFIXO_SELECIONADO = "";
	private String CODIGO_LINHA_SELECIONADO = "0";
	private int TIPO_ONIBUS_SELECIONADO = 0;
	private int PREFIXO_MONITORADO = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_onibus);
		
		SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.tela_onibus.mapa);
		GoogleMap map = mapFragment.getMap();		
		
		_mapaServico = new MapaServico(map,this);
		_mapaServico.habilitaMinhaLocalizacao();

		mostraOpcoes(false);
		recebeLinha();
		carregaOnibus();
		
		controleImageButton(R.tela_onibus.atualizar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				carregaOnibus();
			}
		});
		
		map.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng arg0) {
				mostraOpcoes(false);
			}
		});
		
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				CODIGO_LINHA_SELECIONADO = marker.getTitle();
				TIPO_ONIBUS_SELECIONADO = Integer.parseInt(marker.getSnippet().split(",")[0]);
				PREFIXO_SELECIONADO = marker.getSnippet().split(",")[1];
				mostraOpcoes(true);
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
				String textoDistancia = "Prefixo: %s\nN. Linha: %s \nDestino: %s\nDistância: %s Km";
				LinhaModelo linha = ComumServico.pegaLinha(CODIGO_LINHA_SELECIONADO,_linhaModelos);
				
				if(linha != null)
				{
					try
					{
						new ComumServico();
						String distancia = ComumServico.retornaDistancia(new CoordenadasModelo(marker.getPosition().latitude, marker.getPosition().longitude), new CoordenadasModelo(latLngUsuario.latitude, latLngUsuario.longitude));
						textoDistancia = String.format(textoDistancia,PREFIXO_SELECIONADO,linha.GetLetreiroTipo(),linha.GetDestino(),distancia);
					}
					catch (Exception e) 
					{
						textoDistancia = String.format(textoDistancia,PREFIXO_SELECIONADO,linha.GetLetreiroTipo(),linha.GetDestino(),"#ERROR");
					}
				}
				LinearLayout linearLayout = new LinearLayout(getApplicationContext());
				linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				
				ImageView imageView = new ImageView(getBaseContext());
				imageView.setImageDrawable(getResources().getDrawable(OnibusTipoIdentificador.getImagemOnibusTipoIdentificador(TIPO_ONIBUS_SELECIONADO)));
				linearLayout.addView(imageView);
				
				TextView textView = new TextView(getBaseContext());
				textView.setText(textoDistancia);
				linearLayout.addView(textView);
				
				TextView textViewLotacao = new TextView(getBaseContext());
				textViewLotacao.setText("Lotação: Cheio");
				textViewLotacao.setTypeface(Typeface.DEFAULT_BOLD);
				linearLayout.addView(textViewLotacao);
				return linearLayout;
			}
		});
	
		
		controleImageButton(R.tela_onibus.transito).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			new AlertDialogListControle<TransitoIdentificador>(OnibusActivity.this, "Trânsito", 
				TransitoIdentificador.getTudo(),new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int posicao) {
						mensagem(getText(R.string.geral_envio_com_sucesso).toString());
					}
				}).show();
			}
		});
		
		controleImageButton(R.tela_onibus.lotacao).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialogListControle<OnibusLotacaoIdentificador>(OnibusActivity.this, "Lotação", 
						OnibusLotacaoIdentificador.getTudo(),new android.content.DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int posicao) {
								mensagem(getText(R.string.geral_envio_com_sucesso).toString());
							}
						}).show();
			}
		});
		
		controleImageButton(R.tela_onibus.avaliacao).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialogListControle<OnibusAvaliacaoIdentificador>(OnibusActivity.this, "Avaliação", 
						OnibusAvaliacaoIdentificador.getTudo(),new android.content.DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int posicao) {
								mensagem(getText(R.string.geral_envio_com_sucesso).toString());
							}
						}).show();
			}
		});
	}
	
	public void carregaOnibus()
	{
		AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>()
				{
			
			@Override
					protected void onPreExecute() {
						super.onPreExecute();
						mensagem(getText(R.string.geral_atualizando).toString());
					}
			
			@Override
			protected Void doInBackground(Void... params) {
				
				for (LinhaModelo linha : _linhaModelos) {
					OnibusServico onibusServico = new OnibusServico(OnibusActivity.this);
					linha.SetOnibus(onibusServico.pesquisaOnibus(linha.getCodigoLinha(),EmpresaIdentificador.getPorValor(linha.getEmpresa()) , SentidoIdentificador.getPorValor(linha.getSentido())));
				}
				
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							_mapaServico.limpaMapa();
							mostraOpcoes(false);
							mensagem(getText(R.string.geral_atualizando).toString());
							for (LinhaModelo linha : _linhaModelos) {
								if(linha.getOnibus() != null)
								{
									for (OnibusModelo onibus : linha.getOnibus()) 
									{
										Marcador marcador = new Marcador(linha.getCodigoLinha(),
												String.valueOf(onibus.getType()) + "," + onibus.getPrefixo(),
												PREFIXO_MONITORADO == Integer.parseInt(onibus.getPrefixo()) ? R.drawable.viewbus_icone_area1 : onibus.getIcone(),
												onibus.getLatitudeDouble(),
												onibus.getLongitudeDouble());
										_mapaServico.carregaMarcador(marcador);	
									}
								}
							}
						}
					});	
				return null;
			}
			
			@Override
					protected void onPostExecute(Void result) {
						mensagem(getText(R.string.geral_atualizado_sucesso).toString());
						carregaRota();
					}
		};
		
		asyncTask.execute();
	}
	
	private void carregaRota()
	{
		AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>()
				{
			@Override
			protected Void doInBackground(Void... params) {
				for (LinhaModelo linha : _linhaModelos) {
					if(linha.getRota() == null || linha.getRota().getCoordenates() == null)
						linha.setRota(new RotaServico(OnibusActivity.this).pesquisaRota(linha.getCodigoLinha(),EmpresaIdentificador.getPorValor(linha.getEmpresa())));
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							for (LinhaModelo linha: _linhaModelos) {
								_mapaServico.desenhaRota((ArrayList<LatLng>) new RotaServico(OnibusActivity.this).transformaRotaParaCoordenadas(linha.getRota()),Color.BLUE);
							}
						}
					});	
				}
				return null;
			}
		};
		
		asyncTask.execute();		
	}

	public static void NotificacaoCancelar(Context context)
	{
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.cancelAll();
	}
	
	private void recebeLinha()
	{
		//NotificacaoCancelar(getApplicationContext());
		stopService(new Intent("NotificacaoDistancia"));
		
		
		Intent intent = getIntent();
        Bundle b = intent.getExtras();
        
        if(b.getBoolean("servico"))
        {
        	Log.e("CoO", String.valueOf(b.getInt("monitorar")));
        	PREFIXO_MONITORADO = b.getInt("monitorar");
        }
        
        String[] linhaIds = b.getString("linhaId").split(",");
    	String[] prefixos = b.getString("prefixos").split(",");
    	String[] tipos = b.getString("tipos").split(",");
    	String[] empresas = b.getString("empresas").split(",");
    	String[] sentidos = b.getString("sentidos").split(",");
    	String[] denominacaoTPTS = b.getString("denominacaoTPTS").split(",");
    	String[] denominacaoTSTP = b.getString("denominacaoTSTP").split(",");
    	
    	for (int i = 0; i < linhaIds.length; i++) 
		{
			LinhaModelo linha = new LinhaModelo();
			linha.setCodigoLinha(linhaIds[i]);
			linha.setLetreiro(prefixos[i]);
			linha.setTipo(tipos[i]);
			linha.setEmpresa(Integer.parseInt(empresas[i]));
			linha.setSentido(Integer.valueOf(sentidos[i]));
			linha.setDenominacaoTPTS(denominacaoTPTS[i]);
			linha.setDenominacaoTSTP(denominacaoTSTP[i]);
			_linhaModelos.add(linha);
		}
	}
	
	private OnibusModelo pegaPosicaoLinha(String prefixo)
	{
		if(_linhaModelos != null)
			for (LinhaModelo linha : _linhaModelos) {
				if(linha.getOnibus() != null)
					for (OnibusModelo posicaoLinha : linha.getOnibus()) {
						if(posicaoLinha.getPrefixo().equals(prefixo))
							return posicaoLinha;
			}
		}
		
		return null;
	}

	private void mostraOpcoes(boolean visivel)
	{
		int visivelView = visivel ? View.VISIBLE : View.GONE;
		controleImageButton(R.tela_onibus.avaliacao).setVisibility(visivelView);
		controleImageButton(R.tela_onibus.lotacao).setVisibility(visivelView);
		controleImageButton(R.tela_onibus.transito).setVisibility(visivelView);
		controleImageButton(R.tela_onibus.avaliacao).setVisibility(visivelView);
		controleImageButton(R.tela_onibus.avaliacao).setVisibility(visivelView);
	}
}
