package br.sptrans.transportepublico.identificador;

public class EmpresaIdentificador extends BaseIdentificador<Integer>{


	public EmpresaIdentificador(String nome, Integer valor) {
		super(nome, valor);
	}

	public final static EmpresaIdentificador SPTrans = new EmpresaIdentificador("SPTrans", 1);
	public final static EmpresaIdentificador EMTU = new EmpresaIdentificador("EMTU", 2);
	
	
	public static EmpresaIdentificador getPorValor(Integer valor) {
		
		if(SPTrans.getValor().equals(valor))
			return SPTrans;
		
		if(EMTU.getValor().equals(valor))
			return EMTU;
		
		return null;
	}
}
