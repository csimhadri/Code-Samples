package com.poc.rest.springboot.service;

import com.poc.rest.springboot.domain.Product;
import com.poc.rest.springboot.domain.ProductPrice;
import com.poc.rest.springboot.exception.ProductException;

public interface ProductService {

	/**
	 * retrieveProductName - the product Name is hosted externally
	 * @param productID
	 * @return String productName
	 */
	public String retrieveProductName(final Integer productID) ;

	/**
	 * update
	 * @param id
	 * @param product
	 * @return ProductPrice - the object has the product ID and price
	 * @throws ProductException 
	 */
	public ProductPrice update(final Integer id,final ProductPrice product) throws ProductException ;
	
	/**
	 * create
	 * @param product
	 * @return ProductPrice - object that has the product Id and price
	 */
	public ProductPrice create(final ProductPrice product);
	
	
	/**
	 * find the Product by ID 
	 * @param id
	 * @return Product - the product has the price and name from different sources.
	 * in this implementation, the product name is from an external site and the price is from 
	 * a NOSQL DB.
	 * @throws ProductException 
	 */
	public Product findByID(final Integer id) throws ProductException ;
}
