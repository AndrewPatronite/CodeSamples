/**
 * HashProductDao.java
 * Andrew Patronite
 * October 19, 2013
 * 
 * Hashtable backed DAO for the product/sale repository
 */
package com.jama.dao;

import java.util.Hashtable;

import org.springframework.stereotype.Repository;

import com.jama.model.Product;
import com.jama.model.Sale;

@Repository
public class HashProductDao implements IProductDao
{
	private static Hashtable<Character, Product> products;
	private static Hashtable<Character, Sale> sales;

	public HashProductDao()
	{
		if(null == products)
		{
			products = new Hashtable<Character, Product>();
		}
		if(null == sales)
		{
			sales = new Hashtable<Character, Sale>();
		}
	}

	@Override
	public Product getProduct(Character productName)
	{
		Product product = products.get(productName);

		return product;
	}
	
	@Override
	public void insertProduct(Product product)
	{
		products.put(product.getProductName(), product);
	}

	@Override
	public void updateProduct(Product product)
	{
		products.put(product.getProductName(), product);
	}
}
