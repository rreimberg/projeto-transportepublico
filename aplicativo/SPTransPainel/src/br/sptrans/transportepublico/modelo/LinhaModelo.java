package br.sptrans.transportepublico.modelo;


public class LinhaModelo {

	private String Circular;
	private String CodigoLinha;
	private String DenominacaoTPTS;
	private String DenominacaoTSTP;
	private String Letreiro;
	private int Sentido;
	private String Tipo;
	private int Empresa;
	
	public String getCircular() {
		return Circular;
	}
	public void setCircular(String circular) {
		Circular = circular;
	}
	public String getCodigoLinha() {
		return CodigoLinha;
	}
	public void setCodigoLinha(String codigoLinha) {
		CodigoLinha = codigoLinha;
	}
	public String getDenominacaoTPTS() {
		return DenominacaoTPTS;
	}
	public void setDenominacaoTPTS(String denominacaoTPTS) {
		DenominacaoTPTS = denominacaoTPTS;
	}
	public String getDenominacaoTSTP() {
		return DenominacaoTSTP;
	}
	public void setDenominacaoTSTP(String denominacaoTSTP) {
		DenominacaoTSTP = denominacaoTSTP;
	}
	public String getLetreiro() {
		return Letreiro;
	}
	public void setLetreiro(String letreiro) {
		Letreiro = letreiro;
	}
	public int getSentido() {
		return Sentido;
	}
	public void setSentido(int sentido) {
		Sentido = sentido;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public int getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(int empresa) {
		Empresa = empresa;
	}
	
	@Override
    public String toString() 
    {
	     return getOrigem() + " -> " + getDestino();
	}
    
    public boolean sentidoBairro()
    {
    	return Sentido == 1;
    }
    
    public String getDestino()
    {
    	if(sentidoBairro())
    		return DenominacaoTPTS;
    	else
    		return DenominacaoTSTP;
    }
    
    public String getOrigem()
    {
    	if(sentidoBairro())
    		return DenominacaoTSTP;
    	else
    		return DenominacaoTPTS;
    }
    
    public String getLetreiroTipo()
    {
    	if("0".contains(getTipo()))
    		return getLetreiro();
    	
    	return getLetreiro() + "-" + getTipo();
    }
}
