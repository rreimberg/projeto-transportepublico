package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

import br.sptrans.transportepublico.painel.R;

public class AlertaIdentificador extends BaseIdentificador<Integer> {

	private int _controleId;
	private String _corId;
	
	public AlertaIdentificador(String nome, Integer valor,int controleId,String hexaColor) {
		super(nome, valor);
		_corId = hexaColor;
		_controleId = controleId;
	}

	public static AlertaIdentificador Roubo = new AlertaIdentificador("Roubo", 1, R.tela_alerta.button_roubo, "#000000");
	public static AlertaIdentificador Depredacao = new AlertaIdentificador("Depredação", 2, R.tela_alerta.button_ataque, "#e5e500");
	public static AlertaIdentificador Incendio = new AlertaIdentificador("Incêndio",3, R.tela_alerta.button_incendio, "#ff0000");
	public static AlertaIdentificador Bagunca = new AlertaIdentificador("Bagunça", 4, R.tela_alerta.button_bagunca, "#a52a2a");
	public static AlertaIdentificador SomAlto = new AlertaIdentificador("Som Alto", 5, R.tela_alerta.button_som_alto, "#ffa500");
	public static AlertaIdentificador Outros = new AlertaIdentificador("Outros", 6, R.tela_alerta.button_outros, "#008000");
	
	public int getControleId() {
		return _controleId;
	}

	public void setControleId(int _controleId) {
		this._controleId = _controleId;
	}

	public String getCorId() {
		return _corId;
	}

	public void setCorId(String corId) {
		_corId = corId;
	}
	
	public static List<AlertaIdentificador> retornaTudo()
	{
		List<AlertaIdentificador> alertaIdentificadors = new ArrayList<AlertaIdentificador>();
		alertaIdentificadors.add(Roubo);
		alertaIdentificadors.add(Depredacao);
		alertaIdentificadors.add(Incendio);
		alertaIdentificadors.add(Bagunca);
		alertaIdentificadors.add(SomAlto);
		alertaIdentificadors.add(Outros);
		return alertaIdentificadors;
	}
	
	public static AlertaIdentificador retornaPorValor(int valor)
	{
		for (AlertaIdentificador item : retornaTudo()) {
			if(item.getValor() == valor)
				return item;
		}
		
		return null;
	}
}
