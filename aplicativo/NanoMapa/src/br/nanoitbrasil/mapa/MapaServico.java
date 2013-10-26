package br.nanoitbrasil.mapa;

import java.util.ArrayList;

import android.content.Context;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaServico implements IMapaServico {

	GoogleMap _googleMap;
	public MapaServico(GoogleMap googleMap,Context context)
	{
		_googleMap = googleMap;
		
		try 
		{
			MapsInitializer.initialize(context);
		} 
		catch (GooglePlayServicesNotAvailableException e) {

		}
		
		try
		{
			CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(-23.550356,-46.634219), 11, 0, 0));
			_googleMap.animateCamera(cameraUpdate,2000,null);
		}
		catch (Exception e) {

		}
	}
	
	public void desenhaRota(ArrayList<LatLng> latLngRota,int color)
	{
		PolylineOptions polylineOptions = new PolylineOptions();
		polylineOptions.color(color);
		polylineOptions.width(4);
		
		for (LatLng latLng : latLngRota) {
			polylineOptions.add(latLng);
		}
		
		Polyline polyline = _googleMap.addPolyline(polylineOptions);
		polyline.setGeodesic(true);
	}
	
	
	public void carregaMarcador(Marcador marcador)
	{
		LatLng latLng = new LatLng(marcador.getLatitude(),marcador.getLongitude());
		MarkerOptions mo = new MarkerOptions();
		
		mo.icon(BitmapDescriptorFactory.fromResource(marcador.getImagem()));
			
		mo.position(latLng).title(marcador.getTitulo()).snippet(marcador.getDescricao());
		_googleMap.addMarker(mo);			
	}
	
	@Override
	public void trafego(boolean b)
	{
		_googleMap.setTrafficEnabled(b);
	}
	
	@Override
	public boolean trafegoHabilitado()
	{
		return _googleMap.isTrafficEnabled();
	}
	
	@Override
	public void satelite(boolean b)
	{
		_googleMap.setMapType(b ? GoogleMap.MAP_TYPE_SATELLITE : GoogleMap.MAP_TYPE_NORMAL);
	}
	
	@Override
	public Projection getProjection()
	{
		return _googleMap.getProjection();
	}
	
	@Override
	public LatLng posicaoCamera()
	{
		return _googleMap.getCameraPosition().target;
	}
	
	@Override
	public void limpaMapa()
	{
		_googleMap.clear();
	}
	
	@Override
	public void habilitaMinhaLocalizacao()
	{
		_googleMap.setMyLocationEnabled(true);
	}
	
	@Override
	public void vaiPara(LatLng latLng)
	{
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
	    _googleMap.animateCamera(cameraUpdate);
	}
	
	@Override
	public void vaiPara(LatLng latLng,int zoom)
	{
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
	    _googleMap.animateCamera(cameraUpdate);
	}
	
	public void vaiPara(LatLng latLng,int zoom,int tilt,int bearing)
	{
		CameraPosition cameraPosition = new CameraPosition(latLng, zoom, tilt, bearing);
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		
	    _googleMap.animateCamera(cameraUpdate);
	}
	
	public void zoom(int zoom)
	{
		_googleMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));
	}
}
