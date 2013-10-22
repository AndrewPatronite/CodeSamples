/**
 * Sale.java
 * Andrew Patronite
 * October 20, 2013
 * 
 * Abstract entity representing a Sale
 */
package com.jama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Sale", catalog = "jama")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sale implements ISale
{
	private long productId;
	protected Product product;

	public Sale()
	{
	}
	
	public Sale(Product product)
	{
		this.product = product;
	}
	
	@GenericGenerator(name = "generator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "product"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ProductId", unique = true, nullable = false)
	public long getProductId()
	{
		return productId;
	}

	public void setProductId(long productId)
	{
		this.productId = productId;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}
}
