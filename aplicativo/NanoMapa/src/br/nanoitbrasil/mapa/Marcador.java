package br.nanoitbrasil.mapa;

public class Marcador {

	private String Titulo;
	private String Descricao;
	private int Imagem;
	private double Latitude;
	private double Longitude;
	
	public Marcador(String titulo,String descricao,int imagem,double latitude,double longitude)
	{
		Titulo = titulo;
		Descricao = descricao;
		Imagem = imagem;
		Latitude = latitude;
		Longitude = longitude;
	}
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public int getImagem() {
		return Imagem;
	}
	public void setImagem(int imagem) {
		Imagem = imagem;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
}
