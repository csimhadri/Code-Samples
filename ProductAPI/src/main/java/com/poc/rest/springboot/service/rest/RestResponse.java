package com.poc.rest.springboot.service.rest;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse {

    private Product product;
    

    public RestResponse() {
    }



	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}



	/**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
