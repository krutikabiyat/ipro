package com.intel.dao;

import java.util.List;

import com.intel.domain.Product;
/**
 * @author Krutika Mohanty
 * Abstract operations
 */
public interface ProductDAO {
    /**
     * Add product
     * @param product
     */
    void add(Product product);
    /**
     * Retrieve all products
     * @return
     */
    List<Product> viewAll();
    /**
     * Retrieve one product
     * @param id
     * @return
     */
    Product viewById(String id);
    /**
     * Delete product
     * @param id
     * @return
     */
    boolean deleteById(String id);
    /**
     * Update a product
     * @param product
     * @return
     */
    int update(Product product); 
}
