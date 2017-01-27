package br.com.zup.productapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.zup.productapp.run.Main;
import br.com.zup.productapp.util.Constants.Context;
import spark.utils.IOUtils;

public class MainTest {
	@BeforeClass
	public static void beforeClass() {
		EmbeddedMongo.DB.start();
	}

	@AfterClass
	public static void afterClass() {
		//Spark.stop();
	}

	@Test
	public void searchProductTest() throws Exception {
		Main.main(null);
	}
	
	
	public synchronized void teste() throws Exception {
		TestResponse res = request("GET", "/products");
		assertEquals(200, res.status);
	}
	
	
	

	private TestResponse request(String method, String path) {
		try {
			URL url = new URL(TestConstants.APPLICATION_URL + Context.API_CONTEXT + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {
		//public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
		//this.body = body;
		}
		
	}
}
