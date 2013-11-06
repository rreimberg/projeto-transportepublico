package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

import br.sptrans.transportepublico.painel.R;

public class MenuIdentificador extends BaseIdentificador<Integer> {

	private int _controleId;
	private String _corId;
	
	public MenuIdentificador(String nome, Integer valor,int controleId,String hexaColor) {
		super(nome, valor);
		_corId = hexaColor;
		_controleId = controleId;
	}

	public static MenuIdentificador Viagem = new MenuIdentificador("Viagem", 1, R.menu.viagem, "#008000");
	public static MenuIdentificador Transito = new MenuIdentificador("Transito", 2, R.menu.transito, "#cc8400");
	public static MenuIdentificador Lotacao = new MenuIdentificador("Lotacao",3, R.menu.lotacao, "#000066");
	public static MenuIdentificador Alertas = new MenuIdentificador("Alertas", 4, R.menu.alerta, "#990000");

	
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
	
	public static List<MenuIdentificador> retornaTudo()
	{
		List<MenuIdentificador> alertaIdentificadors = new ArrayList<MenuIdentificador>();
		alertaIdentificadors.add(Viagem);
		alertaIdentificadors.add(Transito);
		alertaIdentificadors.add(Lotacao);
		alertaIdentificadors.add(Alertas);
		return alertaIdentificadors;
	}
	
	public static MenuIdentificador retornaPorValor(int valor)
	{
		for (MenuIdentificador item : retornaTudo()) {
			if(item.getValor() == valor)
				return item;
		}
		
		return null;
	}
}
