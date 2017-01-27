package br.com.zup.productapp.util;

import com.google.gson.Gson;
import spark.Response;
import spark.ResponseTransformer;

import java.util.HashMap;

/**
 * @author Luan Roque. 
 * Classe de utilitaria para transformação de objetos
 * para {@link String} json.
 *
 */
public class JsonUtil implements ResponseTransformer {

	/**
	 * Api Gson para conversão de objetos em JSON.
	 */
	private Gson gson = new Gson();

	@Override
	public String render(Object model) throws Exception {
		if (model instanceof Response) {
			return gson.toJson(new HashMap<>());
		}
		return gson.toJson(model);
	}

}