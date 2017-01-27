package br.com.zup.productapp.run;


import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import br.com.zup.productapp.resouce.ProductResource;
import br.com.zup.productapp.service.ProductService;
import br.com.zup.productapp.util.Constants;

/**
 * 
 * @author Luan Roque.
 * Classe de execução do sistema.
 * 
 *
 */
public class Bootstrap {
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 8080;

    /**
     * Método de execução principal do sistema.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        new ProductResource(new ProductService(mongo()));
    }

    /**
     * Configuração de banco de dados Mongo DB.
     * @return Bando de dados {@link DB} 
     * @throws Exception
     */
    private static DB mongo() throws Exception {
    	Logger mongoLogger = Logger.getLogger( "com.mongodb" );
    	mongoLogger.setLevel(Level.INFO);
    	System.setProperty("DB.TRACE", "true");
    	
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(Constants.DataSource.HOST, Constants.DataSource.PORT), mongoClientOptions);
        DB db = mongoClient.getDB(Constants.DataSource.DB_NAME);
        return db;
    }
}
