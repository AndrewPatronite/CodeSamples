/**
 * IProductDaoFactory.java
 * Andrew Patronite
 * October 19, 2013
 * 
 * Factory interface for creating an IProductDao instance
 */
package com.jama.dao;

public interface IProductDaoFactory
{
	IProductDao createProductDao();
}
