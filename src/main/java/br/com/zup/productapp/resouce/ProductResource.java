package br.com.zup.productapp.resouce;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.File;

import static spark.Spark.delete;

import br.com.zup.productapp.service.ProductService;
import br.com.zup.productapp.util.Constants;
import br.com.zup.productapp.util.JsonUtil;

/**
 * Resource para endpoint de produto.
 * @author Luan Roque.
 *
 */
public class ProductResource {
	
	/**
	 * Url de acesso a servicos de produtos
	 */
	private static final String PATH = "/products";

	/**
	 * Parametro de URL id do produto
	 */
	private static final String PATH_PARAM = ":id";

	
	private final ProductService productService;

	public ProductResource(ProductService productservice) {
		this.productService = productservice;
		setupEndpoints();
	}
	/**
	 * Disponibiliza os servicos Rest
	 */
	private void setupEndpoints() {

		get(Constants.Context.API_CONTEXT + PATH, Constants.Context.MEDIA_TYPE,
				(request, response) -> productService.findAll(), new JsonUtil());

		get(Constants.Context.API_CONTEXT + PATH + File.separator + PATH_PARAM, Constants.Context.MEDIA_TYPE,
				(request, response) -> productService.find(request.params(":id")), new JsonUtil());

		post(Constants.Context.API_CONTEXT + PATH, Constants.Context.MEDIA_TYPE, (request, response) -> {
			productService.createNewProduct(request.body());
			response.status(201);
			return response;
		}, new JsonUtil());

		put(Constants.Context.API_CONTEXT + PATH + File.separator + PATH_PARAM, Constants.Context.MEDIA_TYPE,
				(request, response) -> productService.update(request.params(PATH_PARAM), request.body()), new JsonUtil());

		delete(Constants.Context.API_CONTEXT + PATH + File.separator + PATH_PARAM, Constants.Context.MEDIA_TYPE,
				(request, response) -> productService.delete(request.params(PATH_PARAM)), new JsonUtil());
	}

}
