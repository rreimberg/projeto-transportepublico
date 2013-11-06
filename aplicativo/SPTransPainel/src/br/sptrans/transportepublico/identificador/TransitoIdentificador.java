package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

import br.sptrans.transportepublico.painel.R;

public class TransitoIdentificador extends BaseIdentificador<Integer> {

	private int _controleId;
	private String _corId;
	
	public TransitoIdentificador(String nome, Integer valor,int controleId,String hexaColor) {
		super(nome, valor);
		_corId = hexaColor;
		_controleId = controleId;
	}

	public static TransitoIdentificador Livre = new TransitoIdentificador("Livre", 1, R.tela_transito.imagebutton_livre, "#cc8400");
	public static TransitoIdentificador Fluindo = new TransitoIdentificador("Fluindo", 2, R.tela_transito.imagebutton_fluindo, "#cc8400");
	public static TransitoIdentificador Lento = new TransitoIdentificador("Lento",3, R.tela_transito.imagebutton_lento, "#cc8400");
	public static TransitoIdentificador Parado = new TransitoIdentificador("Parado", 4, R.tela_transito.imagebutton_parado, "#cc8400");

	
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
	
	public static List<TransitoIdentificador> retornaTudo()
	{
		List<TransitoIdentificador> alertaIdentificadors = new ArrayList<TransitoIdentificador>();
		alertaIdentificadors.add(Fluindo);
		alertaIdentificadors.add(Lento);
		alertaIdentificadors.add(Livre);
		alertaIdentificadors.add(Parado);
		return alertaIdentificadors;
	}
	
	public static TransitoIdentificador retornaPorValor(int valor)
	{
		for (TransitoIdentificador item : retornaTudo()) {
			if(item.getValor() == valor)
				return item;
		}
		
		return null;
	}
}
