package br.sptrans.transportepublico.mapeamento;

import java.util.List;

import br.sptrans.transportepublico.modelo.PontoLinhaModelo;
import br.sptrans.transportepublico.modelo.PontoModelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PontoMapeamento {

	public List<PontoModelo> deJsonParaPontoModelo(String json)
	{
		return new Gson().fromJson(json, new TypeToken<List<PontoModelo>>(){}.getType());
	}
	
	public List<PontoLinhaModelo> deJsonParaPontoLinhaModelo(String json)
	{
		return new Gson().fromJson(json, new TypeToken<List<PontoLinhaModelo>>(){}.getType());
	}
}
