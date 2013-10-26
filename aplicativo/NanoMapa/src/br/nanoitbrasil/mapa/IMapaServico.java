package br.nanoitbrasil.mapa;

import java.util.ArrayList;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;

public interface IMapaServico {

	public void zoom(int zoom);

	public void vaiPara(LatLng latLng, int zoom, int tilt,int bearing);

	public void vaiPara(LatLng latLng, int zoom);

	public void vaiPara(LatLng latLng);

	public void habilitaMinhaLocalizacao();

	public void limpaMapa();

	public LatLng posicaoCamera();

	public Projection getProjection();

	public void satelite(boolean b);

	public boolean trafegoHabilitado();

	public void trafego(boolean b);

	public void carregaMarcador(Marcador marcador);

	public void desenhaRota(ArrayList<LatLng> latLngRota, int color);

	
}
