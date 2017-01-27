package br.com.zup.productapp.util;

/**
 * Interface de costantes do sistema. 
 * Constantes como interface garantindo unicidade dos dados.
 * @author Luan Roque.
 *
 */
public interface Constants {

	/**
	 * Constantes de configuração de acesso a banco de dados
	 * @author Luan Roque.
	 *
	 */
	public interface DataSource {
		String HOST = "localhost";
		Integer PORT = 27017;
		String DB_NAME = "productsdb";
	}
	
	/**
	 * Constantes de configuração de contexto do sistema.
	 * @author Luan Roque.
	 *
	 */
	public interface Context {
		String API_CONTEXT = "/rest";
		String MEDIA_TYPE = "application/json";
	}
	

}
