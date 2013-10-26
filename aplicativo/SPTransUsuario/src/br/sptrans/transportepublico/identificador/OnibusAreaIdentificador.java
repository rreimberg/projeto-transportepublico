package br.sptrans.transportepublico.identificador;

import br.sptrans.transportepublico.usuario.R;

public class OnibusAreaIdentificador extends BaseIdentificador<Integer>{

	private int _imagem;
	public OnibusAreaIdentificador(String nome, Integer valor,int imagem) {
		super(nome, valor);
		_imagem = imagem;
	}

	public int getImagem() {
		return _imagem;
	}

	public final static OnibusAreaIdentificador Area1 = new OnibusAreaIdentificador("Area 1",1, R.drawable.viewbus_icone_area1);
	public final static OnibusAreaIdentificador Area2 = new OnibusAreaIdentificador("Area 2",2, R.drawable.viewbus_icone_area2);
	public final static OnibusAreaIdentificador Area3 = new OnibusAreaIdentificador("Area 3",3, R.drawable.viewbus_icone_area3);
	public final static OnibusAreaIdentificador Area4 = new OnibusAreaIdentificador("Area 4",4, R.drawable.viewbus_icone_area4);
	public final static OnibusAreaIdentificador Area5 = new OnibusAreaIdentificador("Area 5",5, R.drawable.viewbus_icone_area5);
	public final static OnibusAreaIdentificador Area6 = new OnibusAreaIdentificador("Area 6",6, R.drawable.viewbus_icone_area6);
	public final static OnibusAreaIdentificador Area7 = new OnibusAreaIdentificador("Area 7",7, R.drawable.viewbus_icone_area7);
	public final static OnibusAreaIdentificador Area8 = new OnibusAreaIdentificador("Area 8",8, R.drawable.viewbus_icone_area8);
}
