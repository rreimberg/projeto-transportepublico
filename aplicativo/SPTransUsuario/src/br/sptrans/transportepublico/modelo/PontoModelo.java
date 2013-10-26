package br.sptrans.transportepublico.modelo;

public class PontoModelo {
	private int Id;
	private double Distance;
	private String Latitude;
	private String Longitude;
	private String Name;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public double getDistance() {
		return Distance;
	}
	public void setDistance(double distance) {
		Distance = distance;
	}
	public String getLatitude() {
		return Latitude;
	}
	
	public Double getLatitudeDouble() {
		return Double.valueOf(Latitude) / 1e6;
	}
	
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	
	public Double getLongitudeDouble() {
		return Double.valueOf(Longitude) / 1e6;
	}
	
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
