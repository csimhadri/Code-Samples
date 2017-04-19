package com.poc.rest.springboot.controller;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.rest.springboot.bean.ErrorResponse;
import com.poc.rest.springboot.domain.Money;
import com.poc.rest.springboot.domain.Product;
import com.poc.rest.springboot.domain.ProductPrice;
import com.poc.rest.springboot.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

	@LocalServerPort
	private int port;

	private URL base;
	private String baseGet;
	private String basePut;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/ProductAPI/products");
		baseGet = "http://localhost:" + port + "/ProductAPI/products/id/";
		basePut = "http://localhost:" + port + "/ProductAPI/products/id/";
	}

	@Test
	public void testPostGetNoName() throws Exception {
		validatePostGet(1, 125.00, "", false);
	}

	@Test
	public void testPostGetWithName() throws Exception {
		validatePostGet(13860428, 225.00, "The Big Lebowski (Blu-ray)", true);

	}

	@Test
	public void testPostGetWithName2() throws Exception {
		validatePostGet(16696652, 325.00, "Beats Solo 2 Wireless - Black (MHNG2AM/A)", true);

	}

	@Test
	public void testPutGetNoName() throws Exception {
		validatePutGet(1, 125.00, "", false);
	}

	@Test
	public void testPutGetWithName() throws Exception {
		validatePutGet(13860428, 225.00, "The Big Lebowski (Blu-ray)", true);

	}

	@Test
	public void testPutGetWithName2() throws Exception {
		validatePutGet(16696652, 325.00, "Beats Solo 2 Wireless - Black (MHNG2AM/A)", true);

	}

	@Test
	public void testHappyPathPostGet() throws MalformedURLException, URISyntaxException {
		Integer id = getRandomId();
		Double amt = getRandomPrice();
		// Post a productPrice and verify using get
		this.validatePostGet(id, amt, null, false);
	}

	@Test
	public void testHappyPathPostGetPutGet() throws MalformedURLException, URISyntaxException {
		Integer id = getRandomId();
		Double amt = getRandomPrice();

		// Post a productPrice and verify using get
		this.validatePostGet(id, amt, null, false);

		// update using put and verify using get
		Double putAmt = getRandomPrice();
		this.validatePutGet(id, putAmt, null, false);

	}

	@Test
	public void testGetFailsWhenNoID() throws MalformedURLException, URISyntaxException {
		Integer id = getRandomId();
		Double amt = getRandomPrice();
		ProductPrice prd = createProductPrice(id, amt);

		URL geturi = new URL(baseGet + prd.id);
		ResponseEntity<ErrorResponse> response = template.getForEntity(geturi.toString(), ErrorResponse.class);

		assert (response.getBody().getMessage().equals(ProductServiceImpl.GET_MSG));
		assert (response.getBody().getErrorCode() == HttpStatus.NOT_FOUND.value());

	}

	@Test
	public void testPutFailsWhenNoId() throws MalformedURLException, URISyntaxException {
		Integer id = getRandomId();
		Double amt = getRandomPrice();
		ProductPrice prd = createProductPrice(id, amt);

		URL puturi = new URL(basePut + prd.id);
		ResponseEntity<ErrorResponse> response = template.exchange(puturi.toURI(), HttpMethod.PUT,
				new HttpEntity<ProductPrice>(prd), ErrorResponse.class);
		assert (response.getBody().getMessage().equals(ProductServiceImpl.UPD_MSG));
		assert (response.getBody().getErrorCode() == HttpStatus.NOT_FOUND.value());

	}

	private void validatePostGet(Integer id, double amt, String name, boolean validateName)
			throws URISyntaxException, MalformedURLException {
		ProductPrice prd = createProductPrice(id, amt);
		template.postForObject(base.toURI(), prd, ProductPrice.class);
		validateGetResults(id, amt, name, validateName, prd);
	}

	private void validatePutGet(Integer id, double amt, String name, boolean validateName)
			throws URISyntaxException, MalformedURLException {
		ProductPrice prd = createProductPrice(id, amt);
		URL puturi = new URL(basePut + prd.id);
		template.put(puturi.toURI(), prd);
		// do a get to ensure what you put is retrieved back
		validateGetResults(id, amt, name, validateName, prd);
	}

	private void validateGetResults(Integer id, double amt, String name, boolean validateName, ProductPrice prd)
			throws MalformedURLException {
		URL geturi = new URL(baseGet + prd.id);
		ResponseEntity<Product> response = template.getForEntity(geturi.toString(), Product.class);

		assert (response.getBody().getPrice().getAmount().equals(BigDecimal.valueOf(amt)));
		assert (response.getBody().id == id);
		if (validateName) {
			assertTrue(response.getBody().getName().equals(name));
		} else {
			assert (response.getBody().getName() == null);
		}
	}

	private Integer getRandomId() {
		Random rn = new Random();
		return rn.nextInt();
	}

	private Double getRandomPrice() {
		Random rn = new Random();
		return rn.nextDouble();
	}

	private ProductPrice createProductPrice(Integer id, double amt) {
		ProductPrice prd = new ProductPrice();
		prd.id = id;
		prd.setPrice(new Money(BigDecimal.valueOf(amt)));
		return prd;
	}

}
