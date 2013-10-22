/**
 * HashProductDaoFactory.java
 * Andrew Patronite
 * October 19, 2013
 * 
 * Factory for creating a HashProductDao instance
 */
package com.jama.dao;

import org.springframework.stereotype.Component;

@Component
public abstract class HashProductDaoFactory implements IProductDaoFactory
{
	public abstract IProductDao createProductDao();
}