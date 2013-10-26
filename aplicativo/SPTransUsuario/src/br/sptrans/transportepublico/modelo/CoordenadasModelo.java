package br.sptrans.transportepublico.modelo;

public class CoordenadasModelo {

	private double Latitude;
	private double Longitude;

	public CoordenadasModelo(double latitude,double longitude)
	{
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double _latitude) {
		this.Latitude = _latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double _longitude) {
		this.Longitude = _longitude;
	}	
}
