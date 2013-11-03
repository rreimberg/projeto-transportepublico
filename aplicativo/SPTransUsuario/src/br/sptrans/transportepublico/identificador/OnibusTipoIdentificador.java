package br.sptrans.transportepublico.identificador;

import br.sptrans.transportepublico.usuario.R;

public class OnibusTipoIdentificador extends BaseIdentificador<Integer>{

	private int _imagem;
	public OnibusTipoIdentificador(String nome, Integer valor,int imagem) {
		super(nome, valor);
		_imagem = imagem;
	}
	
	public int getImagem() {
		return _imagem;
	}

	public static OnibusTipoIdentificador Indefinido = new OnibusTipoIdentificador("Indefinido",1,R.drawable.desconhecido);
	public static OnibusTipoIdentificador Padrao = new OnibusTipoIdentificador("Padrão",2,R.drawable.comum);
	public static OnibusTipoIdentificador MicroOnibus = new OnibusTipoIdentificador("Micro-Ônibus",3,R.drawable.microonibus);
	public static OnibusTipoIdentificador Articulado = new OnibusTipoIdentificador("Articulado",4,R.drawable.articulado);
	public static OnibusTipoIdentificador BiArticulado = new OnibusTipoIdentificador("Bi-Articulado",5,R.drawable.biarticulado);
	
	public static int getImagemOnibusTipoIdentificador(int id)
	{
		if(Padrao.getValor() == 2)
			return Padrao.getImagem();
		
		if(MicroOnibus.getValor() == 3)
			return MicroOnibus.getImagem();
		
		if(Articulado.getValor() == 4)
			return Articulado.getImagem();
		
		if(BiArticulado.getValor() == 5)
			return BiArticulado.getImagem();
		
		
		return Indefinido.getImagem();		
	}

	public static String getNome(int id)
	{
		if(Padrao.getValor() == 2)
			return Padrao.getNome();
		
		if(MicroOnibus.getValor() == 3)
			return MicroOnibus.getNome();
		
		if(Articulado.getValor() == 4)
			return Articulado.getNome();
		
		if(BiArticulado.getValor() == 5)
			return BiArticulado.getNome();
		
		
		return Indefinido.getNome();	
	}
}
