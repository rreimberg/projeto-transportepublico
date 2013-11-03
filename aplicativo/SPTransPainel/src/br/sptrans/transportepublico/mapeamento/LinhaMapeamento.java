
package br.sptrans.transportepublico.mapeamento;

import java.lang.reflect.Type;
import java.util.List;


import br.sptrans.transportepublico.modelo.LinhaModelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LinhaMapeamento {

	public List<LinhaModelo> deJson(String json)
	{ 
		return new Gson().fromJson(json, pegaTipo());
	}
	
	private Type pegaTipo()
	{
		return new TypeToken<List<LinhaModelo>>(){}.getType();
	}
}
