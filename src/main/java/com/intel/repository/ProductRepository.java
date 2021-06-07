package com.intel.repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import com.intel.domain.Product;

/**
 * @author Krutika Mohanty
 * Data store for iProd factory. Also contauns operations to manipulate data.
 * This is a Singleton implementation.
 */
public class ProductRepository {
    private static ProductRepository single_instance = null;

    /**
     * Private Constructor
     */
    private ProductRepository() {
    }

    /**
     * Get Instance to create a Singleton
     * @return
     */
    public static ProductRepository getInstance()
    {
        if (single_instance == null)
            single_instance = new ProductRepository();
  
        return single_instance;
    }
    public ArrayList<Product> products=new ArrayList<Product>();

    /**
     * Database
     */
    public void initialize(){
        products.add(new Product("Modem", "H1111", 23.45, 5));
        products.add(new Product("BlackBox", "B2222", 89.24,95));
        products.add(new Product("Router", "RTR456", 77.34, 56));
        products.add(new Product("Switch", "SW6353", 98.28, 31));
    }

    /**
     * Add a product
     * @param product
     */
    public void add(Product product){
        products.add(product);
    }

    /**
     * Update Poduct
     * @param inputProduct
     * @return
     */
    public int update(Product inputProduct){
        ListIterator<Product> itr = products.listIterator();
        boolean found = false;
        while (itr.hasNext())
        {
            Product product = (Product)itr.next();
            if (product.getId().equalsIgnoreCase(inputProduct.getId())){
                itr.remove();
                found = true;
            }
        }
        if(found){
            products.add(inputProduct);
            return 1;
        } 
        return 0;  
    }

    /**
     * Delete a product
     * @param id
     * @return
     */
    public boolean delete(String id){
        Iterator<Product> itr = ProductRepository.getInstance().products.iterator();
        while (itr.hasNext())
        {
            Product product = (Product)itr.next();
            if (product.getId().equalsIgnoreCase(id)){
                itr.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve a single product
     * @param id
     * @return
     */
    public Product view(String id) {
        for(Product product:ProductRepository.getInstance().products){
            if(product.getId().equalsIgnoreCase(id)){
                return product;
            }
        }
        return null;
    }
}
