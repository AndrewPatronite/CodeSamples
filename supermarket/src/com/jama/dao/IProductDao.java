/**
 * IProductDaoFactory.java
 * Andrew Patronite
 * October 19, 2013
 * 
 * Interface for a product DAO
 */
package com.jama.dao;

import com.jama.model.Product;

public interface IProductDao
{
	Product getProduct(Character productName);
	void insertProduct(Product product);
	void updateProduct(Product product);
}
