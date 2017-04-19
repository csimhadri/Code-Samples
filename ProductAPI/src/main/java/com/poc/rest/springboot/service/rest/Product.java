package com.poc.rest.springboot.service.rest;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Product domain object holds information on product including price
 * 
 *  */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
	private static final long serialVersionUID = -6633442539754014842L;
	
	
	private Item item;




	/**
     * Constructs a <code>String</code> with all attributes in name = value
     * format.
     *
     * @return a <code>String</code> representation of this object.
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }




	public Item getItem() {
		return item;
	}




	public void setItem(Item item) {
		this.item = item;
	}

}
