/**
 * XForThePriceOfYSale.java
 * Andrew Patronite
 * October 20, 2013
 * 
 * X for the price of Y sale
 */
package com.jama.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="XForThePriceOfYSale")
public class XForThePriceOfYSale extends Sale
{
	int x;
	int y;

	public XForThePriceOfYSale()
	{
	}

	public XForThePriceOfYSale(int x, int y, Product product)
	{
		super(product);
		this.x = x;
		this.y = y;
	}

	@Column(name="X")
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	
	@Column(name="Y")
	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public int calculateSalePrice(int quantity)
	{
		int timesEligibleForDiscountPrice = quantity / x;
		int timesForRegularPrice = quantity % x;
		double discountPrice = ((double) y / x) * product.getPrice();
		double discountTotal = timesEligibleForDiscountPrice * discountPrice * x;
		int regularTotal = timesForRegularPrice * product.getPrice(); 
		int totalPrice = ((int) Math.round(discountTotal)) + regularTotal;
		
		return totalPrice;
	}
}
