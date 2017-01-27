package br.com.zup.productapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import br.com.zup.productapp.model.Product;
import br.com.zup.productapp.service.ProductService;
import br.com.zup.productapp.util.Constants;
import br.com.zup.productapp.util.JsonUtil;
/**
 * Teste de servico de produto para operações em banco dados.
 * Ultilização de banco de dados em memoria para os testes.
 * @author Luan Roque
 *
 */
public class ProductServiceTest {
	
	/**
	 * Classe de serviço
	 */
	private ProductService service;
	
	
	/**
	 * Inicia o banco de dados em memória.
	 * @throws Exception
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		EmbeddedMongo.DB.start();
	}

	/**
	 * Para o servico de banco de dados 
	 */
	@AfterClass
	public static void afterClass() {
		EmbeddedMongo.DB.stop();
	}

	/**
	 * Teste de conjunto CRUD de produto.
	 * @throws Exception
	 */
	@Test
	public void crudTest() throws Exception {
		
		JsonUtil jsonUtil = new JsonUtil();
		
		this.service = new ProductService(mongoEmbedded());
		Product product = new Product.ProductBuilder("Pão de Queijo", "Pão de queijo forno de minas.", new Double(2.5), "COMIDA" ).build();
		this.service.createNewProduct(jsonUtil.render(product));
		
		List<Product> products = service.findAll();
		assertNotNull(products);
		assertEquals(products.isEmpty(), false);
		
		Product productFind = this.service.find(products.get(0).getId());
		assertNotNull(productFind);
		assertNotNull(productFind.getId());
		assertNotNull(productFind.getName());
		assertNotNull(productFind.getDescription());
		assertNotNull(productFind.getPrice());
		assertNotNull(productFind.getCategory());
		
		productFind.setPrice(new Double(4.50));
		Product productUpdate = this.service.update(productFind.getId(), jsonUtil.render(productFind));
		assertEquals(productFind.getPrice(), productUpdate.getPrice());
		
		
		String productDeletedId = this.service.delete(productFind.getId());
		assertNotNull(productDeletedId);
		
	}

	/**
     * Configuração de banco de dados Mongo DB imbutido para Teste.
     * @return Bando de dados {@link DB} 
     * @throws Exception
     */
    private static synchronized DB mongoEmbedded() throws Exception {
    	Logger mongoLogger = Logger.getLogger( "com.mongodb" );
    	mongoLogger.setLevel(Level.INFO);
    	System.setProperty("DB.TRACE", "true");
    	
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(Constants.DataSource.HOST, Constants.DataSource.PORT), mongoClientOptions);
        DB db = mongoClient.getDB(Constants.DataSource.DB_NAME);
        return db;
    }
	
}