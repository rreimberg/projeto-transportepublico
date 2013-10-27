package br.sptrans.transportepublico.identificador;

public class SentidoIdentificador extends BaseIdentificador<Integer>{

	public SentidoIdentificador(String nome, int valor) {
		super(nome, valor);
	}

	public static SentidoIdentificador Bairro = new SentidoIdentificador("Bairro", 1);
	public static SentidoIdentificador Centro = new SentidoIdentificador("Centro", 2);
	
	public static SentidoIdentificador getPorValor(Integer valor) {
		
		if(Bairro.getValor().equals(valor))
			return Bairro;
		
		if(Centro.getValor().equals(valor))
			return Centro;
		
		return null;
	}
}
