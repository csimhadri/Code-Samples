package com.poc.rest.springboot.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.poc.rest.springboot.domain.ProductPrice;

public interface ProductRepository extends MongoRepository<ProductPrice, String> {

	public default void update(ProductPrice product) {
		ProductPrice productDB = this.findOne(Integer.toString(product.id));
		if (productDB != null) {
			productDB.setPrice(product.getPrice());
			this.save(productDB);
		} else
			this.save(product);
	}
}
