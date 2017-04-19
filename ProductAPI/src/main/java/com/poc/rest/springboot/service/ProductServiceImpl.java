package com.poc.rest.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.poc.rest.springboot.domain.Product;
import com.poc.rest.springboot.domain.ProductPrice;
import com.poc.rest.springboot.exception.ProductException;
import com.poc.rest.springboot.service.rest.RestResponse;

@Repository
public class ProductServiceImpl implements ProductService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Autowired
	private ProductRepository productRepository;
	
	public static String GET_MSG = "Product not found for the provided id";
	public static String UPD_MSG = "Product not found. update failed";
	/**
	 * Use MongoTemplate (Note the MongoRepository forced the ID to be String.
	 * @param ID
	 * @return
	 */
	private ProductPrice findProductPriceByID(Integer ID) {
		ProductPrice prd = 
		mongoTemplate.findById(ID, ProductPrice.class);
		return prd;  
	}
	
	@Override
	public String retrieveProductName(Integer productID) {
		RestTemplate restTemplate = new RestTemplate();
		String nameURL = createURL(productID);
		try {
		RestResponse resp = restTemplate.getForObject(nameURL, RestResponse.class);
		
			String productName =  resp.getProduct().getItem().getProductDescription().getTitle();
			return productName;
		} catch (Exception e) {
			//404 is returned if the productId does not exist
			
			return null;
		}

	}

	//TODO externalize URL
	private String createURL(final Integer productID) {
		return String.format(
				"http://redsky.target.com/v1/pdp/tcin/%s?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics",
				productID);
	}



	@Override
	public ProductPrice update(final Integer id, ProductPrice product) throws ProductException{
		ProductPrice productPrice = findProductPriceByID(id);
		if (productPrice == null){
			throw new ProductException(UPD_MSG);
		}
		return productRepository.save(product);
		
	}

	@Override
	public ProductPrice create(ProductPrice product) {
		return productRepository.save(product);
		
	}
	
	@Override
	public Product findByID(final Integer id) throws ProductException {
		ProductPrice productPrice = findProductPriceByID(id);
		String name = retrieveProductName(id);
		if(productPrice == null && name == null){
			throw new ProductException(GET_MSG);
		}
		if (productPrice != null) {

			Product product = new Product(id, name, productPrice.getPrice());
			return product;
		}
		Product product = new Product(id, name, null);
		return product;
	}

	

}
