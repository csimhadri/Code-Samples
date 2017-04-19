package com.poc.rest.springboot.service.rest;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Product domain object holds information on product including price
 * 
 *  */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {
	private static final long serialVersionUID = -6633442539754014842L;

	private String tcin;
	@JsonProperty("product_description")
	private ProductDescription productDescription;

	
	/**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


	public String getTcin() {
		return tcin;
	}


	public void setTcin(String tcin) {
		this.tcin = tcin;
	}


	public ProductDescription getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}



}
