package br.sptrans.transportepublico.mapeamento;

import java.lang.reflect.Type;
import java.util.List;

import br.sptrans.transportepublico.modelo.OnibusModelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OnibusMapeamento {

	public List<OnibusModelo> deJsonParaOnibusModelo(String json)
	{
		return new Gson().fromJson(json, pegaTipo());
	}
	
	private Type pegaTipo()
	{
		return new TypeToken<List<OnibusModelo>>(){}.getType();
	}
}
