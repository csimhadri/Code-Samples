package com.poc.rest.springboot.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;


/**
 * Product domain object holds information on product including price
 * 
 *  */


public class ProductPrice implements Serializable {
	private static final long serialVersionUID = -6633442539754014842L;
	
	@Id  
	public Integer id;
	
	private Money price;
	
	
	
	public Money getPrice() {
		return price;
	}



	public void setPrice(Money price) {
		this.price = price;
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
