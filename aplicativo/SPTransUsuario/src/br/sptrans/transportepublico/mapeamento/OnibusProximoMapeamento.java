package br.sptrans.transportepublico.mapeamento;

import br.sptrans.transportepublico.modelo.OnibusProximoModelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OnibusProximoMapeamento {

	public OnibusProximoModelo deJson(String json)
	{
		if(json == null || json.length() == 0)
			return null;
		
		return new Gson().fromJson(json, new TypeToken<OnibusProximoModelo>(){}.getType());
	}
}
