package com.poc.rest.springboot.controller;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.rest.springboot.bean.ErrorResponse;
import com.poc.rest.springboot.domain.Product;
import com.poc.rest.springboot.domain.ProductPrice;
import com.poc.rest.springboot.exception.ProductException;
import com.poc.rest.springboot.service.ProductService;

/**
 * ProductController is the rest controller for the product API.
 * It supports the crud operations for the product.
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private transient final Logger logger = Logger.getLogger(ProductController.class.getName());

   @Autowired
   ProductService productService;

   
   
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public ResponseEntity<ProductPrice> create(@RequestBody ProductPrice product) {
    	productService.create(product);
        logger.fine("Creating  new product "+product.toString());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT, consumes = "application/json;charset=UTF-8")
    public ResponseEntity<ProductPrice> update(@PathVariable int id,@RequestBody ProductPrice product) throws ProductException {
    	ProductPrice prod = productService.update(id,product);
        logger.fine("Creating  new product "+product.toString());
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable int id) throws ProductException {
        logger.fine("Search all product by productId : "+id);

        Product searchedProduct = productService.findByID(id);
        return new ResponseEntity<>(searchedProduct, HttpStatus.OK);
    }
    
    @ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
