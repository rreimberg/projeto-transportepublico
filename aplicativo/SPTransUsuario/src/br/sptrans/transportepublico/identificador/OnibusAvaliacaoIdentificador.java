package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

public class OnibusAvaliacaoIdentificador extends BaseIdentificador<Integer>{

	public OnibusAvaliacaoIdentificador(String nome, Integer valor) {
		super(nome, valor);
	}
	
	public static OnibusAvaliacaoIdentificador NaoParouPonto = new OnibusAvaliacaoIdentificador("Não parou no ponto", 1);
	public static OnibusAvaliacaoIdentificador DesviouRota = new OnibusAvaliacaoIdentificador("Fora da rota", 2);
	public static OnibusAvaliacaoIdentificador AvancouSinalVermelho = new OnibusAvaliacaoIdentificador("Avançou sinal", 3);
	public static OnibusAvaliacaoIdentificador FaltaRespeitoCobrador = new OnibusAvaliacaoIdentificador("Falta de educação (Cobrador)", 4);
	public static OnibusAvaliacaoIdentificador FaltaRespeitoMotorista = new OnibusAvaliacaoIdentificador("Falta de educação (Motorista)", 5);
	
	
	
	public static List<OnibusAvaliacaoIdentificador> getTudo()
	{
		List<OnibusAvaliacaoIdentificador> transitoIdentificadors = new ArrayList<OnibusAvaliacaoIdentificador>();
		transitoIdentificadors.add(NaoParouPonto);
		transitoIdentificadors.add(DesviouRota);
		transitoIdentificadors.add(AvancouSinalVermelho);
		transitoIdentificadors.add(FaltaRespeitoCobrador);
		transitoIdentificadors.add(FaltaRespeitoMotorista);
		return transitoIdentificadors;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
