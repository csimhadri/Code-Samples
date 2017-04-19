package com.poc.rest.springboot.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Product domain object holds information on product including price
 * 
*/

public class Product implements Serializable {
	private static final long serialVersionUID = -6633442539754014842L;

	public int id;
	private Money price;
	private String name;

	public Product(){
		
	}
	public Product(final int  iD, String name, Money price) {
		id = iD;
		this.name = name;
		this.price = price;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



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
