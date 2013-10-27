package br.sptrans.transportepublico.servico;

import java.util.List;

import android.location.Location;
import br.sptrans.transportepublico.modelo.CoordenadasModelo;
import br.sptrans.transportepublico.modelo.LinhaModelo;

public class ComumServico {

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
	
	public static LinhaModelo pegaLinha(String codigoLinha,List<LinhaModelo> linhaModelos)
	{
		if(linhaModelos != null)
			for (LinhaModelo linha : linhaModelos) {		
				if(codigoLinha.equals(linha.getCodigoLinha()))
					return linha;
		}
		return null;
	}
}
