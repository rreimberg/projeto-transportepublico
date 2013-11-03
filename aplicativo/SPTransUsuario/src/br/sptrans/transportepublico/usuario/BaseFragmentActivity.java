package br.sptrans.transportepublico.usuario;

import java.util.ArrayList;
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

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		locationManager();
	}
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
	
	public void abrirAtividadeMapa(List<LinhaModelo> linhaModelos,Intent i)
	{
		startActivity(abrirAtividadeMapaIntent(linhaModelos,i));
	}
	
	public Intent abrirAtividadeMapaIntent(LinhaModelo linhaModelo,Intent i)
	{
		List<LinhaModelo> linhaModelos = new ArrayList<LinhaModelo>();
		linhaModelos.add(linhaModelo);
		return abrirAtividadeMapaIntent(linhaModelos,i);
	}
	public Intent abrirAtividadeMapaIntent(List<LinhaModelo> linhaModelos,Intent i)
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
		
		//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("linhaId",linhaIds);
		i.putExtra("prefixos",prefixos);
		i.putExtra("tipos",tipos);
		i.putExtra("sentidos",sentidos);
		i.putExtra("empresas", empresas);
		i.putExtra("denominacaoTPTS", denominacaoTPTS);
		i.putExtra("denominacaoTSTP", denominacaoTSTP);
		return i;
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
			latLngUsuario = new LatLng(location.getLatitude(), location.getLongitude());
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
	
	protected void locationManager() {
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
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(locationManager != null)
			locationManager.removeUpdates(this);
	}
	
	
}
