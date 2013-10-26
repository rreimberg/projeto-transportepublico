package br.sptrans.transportepublico.modelo;

import br.sptrans.transportepublico.identificador.OnibusAreaIdentificador;


public class OnibusModelo {

		private String p;
		private String px;
	    private String py;
	    private int type;
		
		public String getPrefixo() {
			return p;
		}
		public void setPrefixo(String p) {
			this.p = p;
		}
		public String getLatitude() {
			return py;
		}
		public void setLatitude(String py) {
			this.py = py;
		}
		public String getLongitude() {
			return px;
		}
		public void setLongitude(String px) {
			this.px = px;
		}
		
		public Double getLongitudeE6()
		{
			return Double.valueOf(px) * 1e6;
		}
		
		public Double getLatitudeE6()
		{
			return Double.valueOf(py) * 1e6;
		}
		
		public Double getLongitudeDouble()
		{
			return Double.valueOf(px);
		}
		
		public Double getLatitudeDouble()
		{
			return Double.valueOf(py);
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		
		public int getIcone()
		{
			int area = Integer.valueOf(getPrefixo().substring(0, 1));
			
			if(OnibusAreaIdentificador.Area1.getValor() == area)
				return OnibusAreaIdentificador.Area1.getImagem();
			
			if(OnibusAreaIdentificador.Area2.getValor() == area)
				return OnibusAreaIdentificador.Area2.getImagem();
			
			if(OnibusAreaIdentificador.Area3.getValor() == area)
				return OnibusAreaIdentificador.Area3.getImagem();
			
			if(OnibusAreaIdentificador.Area4.getValor() == area)
				return OnibusAreaIdentificador.Area4.getImagem();
			
			if(OnibusAreaIdentificador.Area5.getValor() == area)
				return OnibusAreaIdentificador.Area5.getImagem();
			
			if(OnibusAreaIdentificador.Area6.getValor() == area)
				return OnibusAreaIdentificador.Area6.getImagem();
			
			if(OnibusAreaIdentificador.Area7.getValor() == area)
				return OnibusAreaIdentificador.Area7.getImagem();
			
			if(OnibusAreaIdentificador.Area8.getValor() == area)
				return OnibusAreaIdentificador.Area8.getImagem();
			
			return OnibusAreaIdentificador.Area6.getImagem();
		}
}
