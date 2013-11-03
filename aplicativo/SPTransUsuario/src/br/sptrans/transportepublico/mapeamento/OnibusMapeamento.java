package br.sptrans.transportepublico.mapeamento;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.sptrans.transportepublico.modelo.OnibusModelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OnibusMapeamento {

	public List<OnibusModelo> deJsonParaOnibusModelo(String json)
	{
		List<OnibusModelo> onibusModelos = new ArrayList<OnibusModelo>();
		try
		{
			onibusModelos = new Gson().fromJson(json, pegaTipo());
		}
		catch (Exception e) {
			Log.e("CoO", json);
		}
		return onibusModelos;
	}
	
	private Type pegaTipo()
	{
		return new TypeToken<List<OnibusModelo>>(){}.getType();
	}
}
