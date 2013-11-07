package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

public class OnibusAbortoIdentificador extends BaseIdentificador<Integer> {

	
	public OnibusAbortoIdentificador(String nome, Integer valor) {
		super(nome, valor);
	}

	public static OnibusAbortoIdentificador Quebrou = new OnibusAbortoIdentificador("Ônibus Quebrou", 1);
	public static OnibusAbortoIdentificador ColisaoComVeiculo = new OnibusAbortoIdentificador("Colisão (não envolvendo outros veículos)", 2);
	public static OnibusAbortoIdentificador ColisaoSemVeiculo = new OnibusAbortoIdentificador("Colisão (envolvendo outros veículos)", 3);
	public static OnibusAbortoIdentificador Acidente = new OnibusAbortoIdentificador("Acidente envolvendo pedestres",4);
	public static OnibusAbortoIdentificador Incencio = new OnibusAbortoIdentificador("Incêndio", 5);
	public static OnibusAbortoIdentificador Outros = new OnibusAbortoIdentificador("Outros", 6);
		
	
	public static List<OnibusAbortoIdentificador> retornaTudo()
	{
		List<OnibusAbortoIdentificador> alertaIdentificadors = new ArrayList<OnibusAbortoIdentificador>();
		alertaIdentificadors.add(Quebrou);
		alertaIdentificadors.add(ColisaoComVeiculo);
		alertaIdentificadors.add(ColisaoSemVeiculo);
		alertaIdentificadors.add(Acidente);
		alertaIdentificadors.add(Incencio);
		alertaIdentificadors.add(Outros);
		return alertaIdentificadors;
	}
	
	public static OnibusAbortoIdentificador retornaPorValor(int valor)
	{
		for (OnibusAbortoIdentificador item : retornaTudo()) {
			if(item.getValor() == valor)
				return item;
		}
		
		return null;
	}
}
