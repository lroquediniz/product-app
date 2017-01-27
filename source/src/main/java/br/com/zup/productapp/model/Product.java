package br.com.zup.productapp.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

/**
 * Classe de modelo de produto
 */
public class Product {
	private String id;
	private String name;
	private String description;
	private Double price;
	private String category;

	/**
	 * Construtor recebendo objeto da base Mongo DB.
	 * 
	 * @param dbObject
	 */
	public Product(BasicDBObject dbObject) {
		this.id = ((ObjectId) dbObject.get(Product.ProductConstants.FIELD_ID)).toString();
		this.name = dbObject.getString(Product.ProductConstants.FIELD_NAME);
		this.description = dbObject.getString(Product.ProductConstants.FIELD_DESCRIPTION);
		this.price = dbObject.getDouble(Product.ProductConstants.FIELD_PRICE);
		this.category = dbObject.getString(Product.ProductConstants.FIELD_CATEGORY);
	}

	/**
	 * Construtor Objeto com campos.
	 * 
	 * @param dbObject
	 */
	public Product(String id, String name, String description, Double price, String category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	/**
	 * Construtor padrao.
	 * 
	 * @param dbObject
	 */
	public Product() {
		super();
	}

	public Product(ProductBuilder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.category = builder.category;
	}

	/*
	 * Getters and setters
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Interface de constantes para campos de modelo da classe de produto.
	 * 
	 * @author Luan Roque.
	 *
	 */
	public interface ProductConstants {

		String FIELD_ID = "_id";
		String FIELD_NAME = "name";
		String FIELD_DESCRIPTION = "description";
		String FIELD_PRICE = "price";
		String FIELD_CATEGORY = "category";
		String COLLECTION_NAME = "products";

	}
	
	/**
	 * Builder de produto para testes.
	 * @author luan
	 *
	 */
	public static class ProductBuilder {

		private String name;
		private String description;
		private Double price;
		private String category;

		public ProductBuilder(String name, String description, Double price, String category) {
			this.name = name;
			this.description = description;
			this.price = price;
			this.category = category;
		}

		public Product build() {
			return new Product(this);
		}

	}

}
