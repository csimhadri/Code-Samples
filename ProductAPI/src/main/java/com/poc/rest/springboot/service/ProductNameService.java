package com.poc.rest.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.rest.springboot.service.rest.RestResponse;

@Service
public interface ProductNameService {

	public default String retrieveProductName(final Integer productID) {

		RestTemplate restTemplate = new RestTemplate();
		String nameURL = createURL(productID);

		RestResponse resp = restTemplate.getForObject(nameURL, RestResponse.class);
		try {
			String productName =  resp.getProduct().getItem().getProductDescription().getTitle();
			return productName;
		} catch (Exception e) {
			//TODO log error
			return null;
		}

	}

	//TODO externalize URL
	default String createURL(final Integer productID) {
		return String.format(
				"http://redsky.target.com/v1/pdp/tcin/%s?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics",
				productID);
	}

	
}
