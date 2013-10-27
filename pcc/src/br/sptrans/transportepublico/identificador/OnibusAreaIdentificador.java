package br.sptrans.transportepublico.identificador;

import br.sptrans.transportepublico.pcc.R;

public class OnibusAreaIdentificador extends BaseIdentificador<Integer>{

	private int _imagem;
	public OnibusAreaIdentificador(String nome, Integer valor,int imagem) {
		super(nome, valor);
		_imagem = imagem;
	}

	public int getImagem() {
		return _imagem;
	}

	public final static OnibusAreaIdentificador Area1 = new OnibusAreaIdentificador("Area 1",1, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area2 = new OnibusAreaIdentificador("Area 2",2, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area3 = new OnibusAreaIdentificador("Area 3",3, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area4 = new OnibusAreaIdentificador("Area 4",4, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area5 = new OnibusAreaIdentificador("Area 5",5, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area6 = new OnibusAreaIdentificador("Area 6",6, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area7 = new OnibusAreaIdentificador("Area 7",7, R.drawable.icone_favorito);
	public final static OnibusAreaIdentificador Area8 = new OnibusAreaIdentificador("Area 8",8, R.drawable.icone_favorito);
}
