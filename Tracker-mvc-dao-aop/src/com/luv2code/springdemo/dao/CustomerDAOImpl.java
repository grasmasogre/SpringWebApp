package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	// @Transactional - changed to service layer whan that was created
	public List<Customer> getCustomers() {
		
		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> query = currentSession.createQuery("from Customer order by last_name", Customer.class);
		
		// execute query and get results
		List<Customer> customers = query.getResultList();
		
		// return the results
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// save object
		// currentSession.save(theCustomer);
		
		// update object is different som combine both
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {

		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
						
		// get object
		Customer customer = currentSession.get(Customer.class, theId);
				
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get a hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
								
		// get object
		Customer customer = currentSession.get(Customer.class, theId);
		currentSession.delete(customer);
	}
	
}
