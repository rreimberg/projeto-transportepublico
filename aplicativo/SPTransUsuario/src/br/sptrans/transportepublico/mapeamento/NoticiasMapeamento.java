package br.sptrans.transportepublico.mapeamento;

import java.lang.reflect.Type;
import java.util.List;

import br.sptrans.transportepublico.modelo.NoticiaModelo;
import br.sptrans.transportepublico.modelo.NoticiaModelo.NoticiaModeloRoot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NoticiasMapeamento {

	public List<NoticiaModelo> deJson(String json)
	{
		NoticiaModeloRoot result = new Gson().fromJson(json, pegaTipo());
		return result.getMessage();
	}
	
	private Type pegaTipo()
	{
		return new TypeToken<NoticiaModeloRoot>(){}.getType();
	}
}
