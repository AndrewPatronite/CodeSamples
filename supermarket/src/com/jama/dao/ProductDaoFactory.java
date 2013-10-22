/**
 * ProductDaoFactory.java
 * Andrew Patronite
 * October 19, 2013
 * 
 * Abstract factory for creating an instances of DbProductDaoFactory and HashProductDaoFactory
 * IProductDaoFactory factories
 */
package com.jama.dao;

import org.springframework.stereotype.Component;

@Component
public abstract class ProductDaoFactory
{
	public abstract IProductDaoFactory createHashProductDaoFactory();
	public abstract IProductDaoFactory createDbProductDaoFactory();
}