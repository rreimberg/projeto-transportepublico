package br.sptrans.transportepublico.mapeamento;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.sptrans.transportepublico.modelo.CoordenadasModelo;
import br.sptrans.transportepublico.modelo.RotaModelo;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RotaMapeamento {

	public RotaModelo deJsonParaRotaModelo(String json)
	{
		RotaModelo rotaModelo = new Gson().fromJson(json, pegaTipo());
		return rotaModelo;
	}
	
	public List<LatLng> deRotaParaLatLng(List<CoordenadasModelo> coordenadasModelos)
	{
		List<LatLng> latLngs = new ArrayList<LatLng>();
		for (CoordenadasModelo coordenadasModelo : coordenadasModelos) {
			latLngs.add(new LatLng(coordenadasModelo.getLatitude(), coordenadasModelo.getLongitude()));
		}
		
		return latLngs;
	}
	
	private Type pegaTipo()
	{
		return new TypeToken<RotaModelo>(){}.getType();
	}
}
