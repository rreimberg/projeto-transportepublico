package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

public class TransitoIdentificador extends BaseIdentificador<Integer>{

	public TransitoIdentificador(String nome, Integer valor) {
		super(nome, valor);
	}
	
	public static TransitoIdentificador Rapido = new TransitoIdentificador("Rápido", 1);
	public static TransitoIdentificador Moderado = new TransitoIdentificador("Moderado", 2);
	public static TransitoIdentificador Lento = new TransitoIdentificador("Lento", 3);
	
	
	public static List<TransitoIdentificador> getTudo()
	{
		List<TransitoIdentificador> transitoIdentificadors = new ArrayList<TransitoIdentificador>();
		transitoIdentificadors.add(Lento);
		transitoIdentificadors.add(Rapido);
		transitoIdentificadors.add(Moderado);
		return transitoIdentificadors;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
