/**
 * DbProductDao.java
 * Andrew Patronite
 * October 20, 2013
 * 
 * Database/Hibernate backed DAO for the product/sale repository
 */
package com.jama.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.jama.model.Product;
import com.jama.model.Sale;
import com.jama.model.XForThePriceOfYSale;

@Repository
public class DbProductDao implements IProductDao
{
	private static SessionFactory sessionFactory;

	public DbProductDao()
	{
		if(null == sessionFactory)
		{
			sessionFactory = getSessionFactory();
		}
	}

	@Override
	public Product getProduct(Character productName)
	{
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product where productName = :productName");

		query.setParameter("productName", productName);

		Product product = (Product) query.uniqueResult();

		return product;
	}
	
	@Override
	public void insertProduct(Product product)
	{
		Session session = sessionFactory.openSession();
 
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
	}

	@Override
	public void updateProduct(Product product)
	{
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		Query query = session.createQuery("from Product where productName = :productName");

		query.setParameter("productName", product.getProductName());

		Product existingProduct = (Product) query.uniqueResult();
		
		existingProduct.setPrice(product.getPrice());
		
		Sale productSale = product.getSale();
		Query saleQuery = session.createQuery("from Sale where productId = :productId");
		
		saleQuery.setParameter("productId", existingProduct.getId());
		Sale existingSale = (Sale) saleQuery.uniqueResult();
		
		if(null != productSale)
		{
			if((null != existingSale) && (existingSale instanceof XForThePriceOfYSale) && (productSale instanceof XForThePriceOfYSale))
			{
				((XForThePriceOfYSale) existingSale).setX(((XForThePriceOfYSale) productSale).getX());
				((XForThePriceOfYSale) existingSale).setY(((XForThePriceOfYSale) productSale).getY());
				
				session.update(existingSale);
			}
			else
			{
				productSale.setProduct(existingProduct);
				session.save(productSale);
			}
		}
		else
		{
			if(null != existingSale)
			{
				session.delete(existingSale);
				existingProduct.setSale(null);
			}
		}
		session.update(existingProduct);
		session.getTransaction().commit();
	}
	
	private static SessionFactory getSessionFactory()
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		return sessionFactory;
	}
}