/**
 * Product.java
 * Andrew Patronite
 * October 20, 2013
 * 
 * Entity representing a Product
 */
package com.jama.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Product", catalog = "jama", uniqueConstraints =
	{ @UniqueConstraint(columnNames = "Name") })
public class Product
{
	public static int quantity;

	private long id;
	private Integer price;
	private Character productName;
	private Sale sale;

	public Product()
	{
	}
	
	public Product(Character productName, Integer price)
	{
		this.productName = productName;
		this.price = price;
	}
	
	@Id
	@GeneratedValue
	@Column(name="ProductId")
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@Column(name="Price")
	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}
	
	@Column(name="Name", nullable=false)
	public Character getProductName()
	{
		return productName;
	}
	
	public void setProductName(Character productName)
	{
		this.productName = productName;
	}
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	public Sale getSale()
	{
		return sale;
	}

	public void setSale(Sale sale)
	{
		this.sale = sale;
	}
}
