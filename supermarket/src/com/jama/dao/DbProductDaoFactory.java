/**
 * DbProductDaoFactory.java
 * Andrew Patronite
 * October 19, 2013
 * 
 * Factory for creating a DbProductDao instance
 */
package com.jama.dao;

import org.springframework.stereotype.Component;

@Component
public abstract class DbProductDaoFactory implements IProductDaoFactory
{
	public abstract IProductDao createProductDao();
}