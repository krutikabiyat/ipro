package com.intel.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.intel.domain.Product;
import com.intel.repository.ProductRepository;

/**
 * @author Krutika Mohanty
 * This is the implementation class that calls our data store to execute
 * transactions.
 */
public class ProductDAOImpl implements ProductDAO{
    private static ProductDAOImpl single_instance = null;
    public static ProductDAOImpl getInstance() {
        if (single_instance == null)
            single_instance = new ProductDAOImpl();
        return single_instance;
    }

    private ProductDAOImpl() {
        
    }
    /**
     * Add a product
     */
    @Override
    public void add(Product product) {
        ProductRepository.getInstance().add(product);
    }

    /**
     * Delete a product
     */
    @Override
    public boolean deleteById(String id) {
        return ProductRepository.getInstance().delete(id);
    }

    /**
     * Update a product
     */
    @Override
    public int update(Product product) {
        return ProductRepository.getInstance().update(product);
    }

    /**
     * Retrieve all products
     */
    @Override
    public List<Product> viewAll() {
        return ProductRepository.getInstance().products;
    }

    /**
     * Retrieve one product
     */
    @Override
    public Product viewById(String id) {
        return ProductRepository.getInstance().view(id);
    }
    
}
