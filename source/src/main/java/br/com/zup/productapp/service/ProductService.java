package br.com.zup.productapp.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import br.com.zup.productapp.model.Product;
import br.com.zup.productapp.model.Product.ProductConstants;

/**
 * Classe de servico para operações CRUD em banco de dados MongoDB.
 * @author Lauan Roque.
 *
 */
public class ProductService {

    private final DBCollection collection;

    /**
     * Construtor do servico que recebe a conexão do banco de dados.
     * @param db {@link DB}
     */
    public ProductService(DB db) {
        this.collection = db.getCollection(ProductConstants.COLLECTION_NAME);
    }

    /**
     * Lista todos os servicos cadastrados no banco de dados.
     * @return produtos - {@link List}
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            products.add(new Product((BasicDBObject) dbObject));
        }
        return products;
    }

    /**
     * Cria uma novo produto por uma string Json.
     * @param json {@link String}
     */
    public void createNewProduct(String json) {
        Product product = new Gson().fromJson(json, Product.class);
        collection.insert(new BasicDBObject(ProductConstants.FIELD_NAME, product.getName()).
        				  append(ProductConstants.FIELD_DESCRIPTION, product.getDescription()).
        				  append(ProductConstants.FIELD_PRICE, product.getPrice()).
        				  append(ProductConstants.FIELD_CATEGORY, product.getCategory()));
    }

    /**
     * Busca um produto na base de dados.
     * @param id
     * @return {@link Product} - produto. 
     */
    public Product find(String id) {
        return new Product((BasicDBObject) collection.findOne(new BasicDBObject(ProductConstants.FIELD_ID, new ObjectId(id))));
    }

    /**
     * Atualiza um produto.
     * @param id - {@link String}
     * @param json {@link String}
     * @return     {@link Product}
     */
    public Product update(String id, String json) {
    	Product product = new Gson().fromJson(json, Product.class);
    	
    	BasicDBObject updateFields = new BasicDBObject();
    	updateFields.put(ProductConstants.FIELD_NAME, product.getName());
    	updateFields.put(ProductConstants.FIELD_DESCRIPTION, product.getDescription());
    	updateFields.put(ProductConstants.FIELD_PRICE, product.getPrice());
    	updateFields.put(ProductConstants.FIELD_CATEGORY, product.getCategory());

    	BasicDBObject setQuery = new BasicDBObject();
    	setQuery.append("$set", updateFields);
        
    	BasicDBObject searchQuery = new BasicDBObject(ProductConstants.FIELD_ID, new ObjectId(product.getId()));

    	this.collection.update(searchQuery, setQuery);
    	 
        return this.find(id);
    }
    
    /**
     * Remove um produto {@link String}
     * @param id
     * @return
     */
    public String delete(String id) {
    	  collection.remove(new BasicDBObject(ProductConstants.FIELD_ID, new ObjectId(id)));
    	  return id;
        
    }
    
    
    
}
