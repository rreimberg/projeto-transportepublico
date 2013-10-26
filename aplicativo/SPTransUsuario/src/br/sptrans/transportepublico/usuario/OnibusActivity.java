package br.sptrans.transportepublico.usuario;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import br.nanoitbrasil.mapa.IMapaServico;
import br.nanoitbrasil.mapa.MapaServico;
import br.nanoitbrasil.mapa.Marcador;
import br.sptrans.transportepublico.identificador.EmpresaIdentificador;
import br.sptrans.transportepublico.identificador.SentidoIdentificador;
import br.sptrans.transportepublico.modelo.LinhaModelo;
import br.sptrans.transportepublico.modelo.OnibusModelo;
import br.sptrans.transportepublico.servico.OnibusServico;
import br.sptrans.transportepublico.servico.RotaServico;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_onibus);
		
		SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.tela_onibus.mapa);
		GoogleMap map = mapFragment.getMap();		
		
		_mapaServico = new MapaServico(map,this);
		_mapaServico.habilitaMinhaLocalizacao();

		recebeLinha();
		carregaOnibus();
		
		controleImageButton(R.tela_onibus.atualizar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				carregaOnibus();
			}
		});
		
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				CODIGO_LINHA_SELECIONADO = marker.getTitle();
				TIPO_ONIBUS_SELECIONADO = Integer.parseInt(marker.getSnippet().split(",")[0]);
				PREFIXO_SELECIONADO = marker.getSnippet().split(",")[1];
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
				ModeloLinha linha = pegaLinha(CODIGO_LINHA_SELECIONADO);
				
				if(linha != null)
				{
					try
					{
						String distancia = Common.retornaDistancia(new Coordenadas(marker.getPosition().latitude, marker.getPosition().longitude), new Coordenadas(latLngUsuario.latitude, latLngUsuario.longitude));
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
				imageView.setImageDrawable(getResources().getDrawable(Common.getImagemTipoOnibus(TIPO_ONIBUS_SELECIONADO)));
				linearLayout.addView(imageView);
				
				TextView textView = new TextView(getBaseContext());
				textView.setText(textoDistancia);
				linearLayout.addView(textView);
				return linearLayout;
			}
		});
	}
	
	public void carregaOnibus()
	{
		AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>()
				{
			@Override
			protected Void doInBackground(Void... params) {
				
				for (LinhaModelo linha : _linhaModelos) {
					OnibusServico onibusServico = new OnibusServico(OnibusActivity.this);
					linha.SetOnibus(onibusServico.pesquisaOnibus(linha.getCodigoLinha(), EmpresaIdentificador.SPTrans, SentidoIdentificador.Bairro));
				}
				
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							_mapaServico.limpaMapa();
							for (LinhaModelo linha : _linhaModelos) {
								for (OnibusModelo onibus : linha.getOnibus()) {
									Marcador marcador = new Marcador(linha.getCodigoLinha(),
											String.valueOf(onibus.getType()) + "," + onibus.getPrefixo(),
											onibus.getIcone(),
											onibus.getLatitudeDouble(),
											onibus.getLongitudeDouble());
									_mapaServico.carregaMarcador(marcador);	
								}
							}
						}
					});	
				return null;
			}
			
			@Override
					protected void onPostExecute(Void result) {
				//mensagem(getText(/R.string)
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

	private void recebeLinha()
	{
		Intent intent = getIntent();
        Bundle b = intent.getExtras();
        
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
			//new Preferencia(BusActivity.this).delete(linha.CodigoLinha);
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
	
	private LinhaModelo pegaLinha(String codigoLinha)
	{
		if(_linhaModelos != null)
			for (LinhaModelo linha : _linhaModelos) {		
				if(codigoLinha.equals(linha.getCodigoLinha()))
					return linha;
		}
		return null;
	}
}
