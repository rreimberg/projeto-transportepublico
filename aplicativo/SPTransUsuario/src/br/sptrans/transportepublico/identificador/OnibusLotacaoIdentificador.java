package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

public class OnibusLotacaoIdentificador extends BaseIdentificador<Integer>{

	public OnibusLotacaoIdentificador(String nome, Integer valor) {
		super(nome, valor);
	}
	
	public static OnibusLotacaoIdentificador VazioComA = new OnibusLotacaoIdentificador("Vazio (com acentos)", 1);
	public static OnibusLotacaoIdentificador VazioSemA = new OnibusLotacaoIdentificador("Vazio (sem acentos)", 2);
	public static OnibusLotacaoIdentificador Normal = new OnibusLotacaoIdentificador("Normal", 3);
	public static OnibusLotacaoIdentificador Cheio = new OnibusLotacaoIdentificador("Cheio", 4);
	public static OnibusLotacaoIdentificador Lotado = new OnibusLotacaoIdentificador("Lotado", 5);
	
	
	
	public static List<OnibusLotacaoIdentificador> getTudo()
	{
		List<OnibusLotacaoIdentificador> transitoIdentificadors = new ArrayList<OnibusLotacaoIdentificador>();
		transitoIdentificadors.add(VazioComA);
		transitoIdentificadors.add(VazioSemA);
		transitoIdentificadors.add(Normal);
		transitoIdentificadors.add(Cheio);
		transitoIdentificadors.add(Lotado);
		return transitoIdentificadors;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
