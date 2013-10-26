package br.sptrans.transportepublico.usuario;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import br.sptrans.transportepublico.modelo.CoordenadasModelo;
import br.sptrans.transportepublico.modelo.LinhaModelo;

import com.google.android.gms.maps.model.LatLng;

public class BaseFragmentActivity extends FragmentActivity implements LocationListener{

	public LocationManager locationManager;
	public LatLng latLngUsuario = null;
	
	public void mensagem(String text)
	{
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
	}
	
	public ImageButton controleImageButton(int id)
	{
		return (ImageButton)findViewById(id);
	}
	
	public Button controleButton(int id)
	{
		return (Button)findViewById(id);
	}
	
	public EditText controleEditText(int id)
	{
		return (EditText)findViewById(id);
	}
	
	public ListView controleListView(int id)
	{
		return (ListView)findViewById(id);
	}
	
	public CheckBox controleCheckBox(int id)
	{
		return (CheckBox)findViewById(id);
	}
	
	public void abrirAtividadeMapa(List<LinhaModelo> linhaModelos)
	{
		String linhaIds = "";
		String prefixos = "";
		String tipos = "";
		String sentidos = "";
		String empresas = "";
		String denominacaoTPTS = "";
		String denominacaoTSTP = "";
		
		for (LinhaModelo linha : linhaModelos) 
		{
			linhaIds += linha.getCodigoLinha() + ",";
			prefixos += linha.getLetreiro() + ",";
			sentidos += linha.getSentido() + ",";
			empresas += linha.getEmpresa() + ",";
			denominacaoTPTS += linha.getDenominacaoTPTS() + ",";
			denominacaoTSTP += linha.getDenominacaoTSTP() + ",";
			tipos += linha.getTipo() + ",";
		}
		
		Intent i = new Intent(getApplicationContext(),OnibusActivity.class);
		//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("linhaId",linhaIds);
		i.putExtra("prefixos",prefixos);
		i.putExtra("tipos",tipos);
		i.putExtra("sentidos",sentidos);
		i.putExtra("empresas", empresas);
		i.putExtra("denominacaoTPTS", denominacaoTPTS);
		i.putExtra("denominacaoTSTP", denominacaoTSTP);
		startActivity(i);
	}
	
	public static String retornaDistancia(CoordenadasModelo pontoA,CoordenadasModelo pontoB)
	{
		Location locationA = new Location("PontoA"); 
		locationA.setLatitude(pontoA.getLatitude());
		locationA.setLongitude(pontoA.getLongitude());
					
		Location locationB = new Location("PontoB");
		locationB.setLatitude(pontoB.getLatitude());
		locationB.setLongitude(pontoB.getLongitude());
		
		String distance = String.valueOf(locationA.distanceTo(locationB) / 1000);
		return distance.substring(0,distance.indexOf(".") + 4);
	}

	@Override
	public void onLocationChanged(Location location) {
		if(location != null)
		{
			latLngUsuario = new LatLng(location.getLatitude(), location.getLongitude());
			mensagem("Posição Atualizada");
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	private void locationManager() {
		try
		{
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(getBestProviderValue((LocationManager)getSystemService(Context.LOCATION_SERVICE)), 1000, 1000, this);
		}
		catch (Exception e) {
		}
	}
	
	public static String getBestProviderValue(LocationManager locationManager)
	{
		Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        String provider = locationManager.getBestProvider(criteria, true);
        return provider;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationManager();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
		mensagem("Removido GPS");
	}
}
