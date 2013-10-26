package br.sptrans.transportepublico.identificador;

public abstract class BaseIdentificador<TTipo> {

	private String _nome;
	private TTipo _valor;
	public BaseIdentificador(String nome,TTipo valor) {
		_nome = nome;
		_valor = valor;
	}
	
	public String getNome()
	{
		return _nome;
	}
	
	public TTipo getValor()
	{
		return _valor;
	}
	
	//public abstract BaseIdentificador<TTipo> getPorValor(TTipo valor);
}
