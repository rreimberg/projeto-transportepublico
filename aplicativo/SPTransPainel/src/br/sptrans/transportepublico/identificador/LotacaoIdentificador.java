package br.sptrans.transportepublico.identificador;

import java.util.ArrayList;
import java.util.List;

import br.sptrans.transportepublico.painel.R;

public class LotacaoIdentificador extends BaseIdentificador<Integer> {

    private int _controleId;
    private String _corId;

    public LotacaoIdentificador(String nome, Integer valor,int controleId,String hexaColor) {
        super(nome, valor);
        _corId = hexaColor;
        _controleId = controleId;
    }

    public static LotacaoIdentificador assentosLivres = new LotacaoIdentificador("Livre", 1, R.tela_lotacao.imagebutton_assentos_livres, "#000066");
    public static LotacaoIdentificador assentosOcupados = new LotacaoIdentificador("Fluindo", 2, R.tela_lotacao.imagebutton_assentos_ocupados, "#000066");
    public static LotacaoIdentificador cheio = new LotacaoIdentificador("Lento",3, R.tela_lotacao.imagebutton_cheio, "#000066");
    public static LotacaoIdentificador lotado = new LotacaoIdentificador("Parado", 4, R.tela_lotacao.imagebutton_lotado, "#000066");


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

    public static List<LotacaoIdentificador> retornaTudo()
    {
        List<LotacaoIdentificador> alertaIdentificadors = new ArrayList<LotacaoIdentificador>();
        alertaIdentificadors.add(assentosLivres);
        alertaIdentificadors.add(assentosOcupados);
        alertaIdentificadors.add(cheio);
        alertaIdentificadors.add(lotado);
        return alertaIdentificadors;
    }

    public static LotacaoIdentificador retornaPorValor(int valor)
    {
        for (LotacaoIdentificador item : retornaTudo()) {
            if(item.getValor() == valor)
                return item;
        }

        return null;
    }
}
