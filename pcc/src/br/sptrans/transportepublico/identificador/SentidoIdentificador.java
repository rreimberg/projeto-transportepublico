package br.sptrans.transportepublico.identificador;

public class SentidoIdentificador extends BaseIdentificador<Integer>{

	public SentidoIdentificador(String nome, int valor) {
		super(nome, valor);
	}

	public static SentidoIdentificador Bairro = new SentidoIdentificador("Bairro", 1);
	public static SentidoIdentificador Centro = new SentidoIdentificador("Centro", 2);
}
