package br.sptrans.transportepublico.modelo;

import java.util.List;

public class RotaModelo {
	
	private int Type;
	private List<CoordenadasModelo> Coordenates;
	
	public int getTipo() {
		return Type;
	}
	public void setTipo(int type) {
		this.Type = type;
	}
	public List<CoordenadasModelo> getCoordenates() {
		return Coordenates;
	}
	public void setCoordenates(List<CoordenadasModelo> coordenates) {
		Coordenates = coordenates;
	}
	
}