package br.sptrans.transportepublico.mapeamento;

import java.lang.reflect.Type;
import java.util.List;
import br.sptrans.transportepublico.modelo.PesquisaModelo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class PesquisaMapeamento {

    public List<PesquisaModelo> deJsonParaModelo(String json)
    {
        return new Gson().fromJson(json, pegaTipoPesquisaModelo());
    }

    private Type pegaTipoPesquisaModelo()
    {
        return new TypeToken<List<PesquisaModelo>>(){}.getType();
    }
}
