package com.intel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.intel.dao.ProductDAO;
import com.intel.dao.ProductDAOImpl;
import com.intel.domain.Product;
import com.intel.repository.ProductRepository;

/**
 * @author Krutika Mohanty
 * iPro Product Manufacturing Software
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        /**
         * Preload data, this is Singleton
         */
        ProductRepository.getInstance().initialize();

        char option = '\0';
        Scanner scanner = new Scanner(System.in);
        /**
         * Print Banner
         */
        InputStream is = null;
        InputStreamReader streamReader = null;
        BufferedReader reader = null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            is = classloader.getResourceAsStream("banner.iban");
            streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /**
         * Welcome Message
         */
        System.out.println("Welcome to iPro Product Manufacturing.");
        System.out.println("A. Add Product");
        System.out.println("B. View All Products");
        System.out.println("C. View Product");
        System.out.println("D. Delete Product");
        System.out.println("E. Update Product");
        System.out.println("F. Exit");
        boolean exitFlag = false;
        do {
            /**
             * User will enter once and will continue until chooses to logout.
             */
            System.out.println("Choose trasaction type, enter A, B, C, D, E or F ");
            option = scanner.next().charAt(0);
            String id="", name="";
            int quantity=0;
            double price=0.0;
            Product product = null;
            
            switch (option) {
                /**
                 * Add Product
                 */
                case 'A':
                    System.out.println("Add Product");
                    System.out.println("Enter Product ID: ");
                    id = scanner.next();
                    System.out.println("Enter Product Name: ");
                    name = scanner.next();
                    System.out.println("Enter Product Price: ");
                    price = scanner.nextDouble();
                    System.out.println("Enter Product Quantity: ");
                    quantity = scanner.nextInt();
                    product = new Product(name, id, price, quantity);
                    ProductDAOImpl.getInstance().add(product);
                    System.out.println("Successfully added the product to the list. ");
                    System.out.println("\n");
                    break;
                /**
                 * View All Products
                 */
                case 'B':
                    System.out.println("View All Products");
                    System.out.println(ProductDAOImpl.getInstance().viewAll());
                    System.out.println("\n");
                    break;
                /**
                 * View Product
                 */
                case 'C':
                    System.out.println("View Product");
                    System.out.println("Enter Product ID: ");
                    id = scanner.next();
                    System.out.println(ProductDAOImpl.getInstance().viewById(id));
                    System.out.println("\n");
                    break;
                /**
                 * Delete Product
                 */
                case 'D':
                    System.out.println("Delete Product");
                    System.out.println("Enter Product ID: ");
                    id = scanner.next();
                    boolean result = ProductDAOImpl.getInstance().deleteById(id);
                    if(result)
                        System.out.println("Deleted Product: "+id);
                    else 
                        System.out.println("Could not find the product: "+id);
                    System.out.println("\n");
                    break;
                /**
                 * Update Product
                 */
                case 'E':
                    System.out.println("Update Product");
                    System.out.println("Enter Product ID: ");
                    id = scanner.next();
                    System.out.println("Enter Product Name: ");
                    name = scanner.next();
                    System.out.println("Enter Product Price: ");
                    price = scanner.nextDouble();
                    System.out.println("Enter Product Quantity: ");
                    quantity = scanner.nextInt();
                    product = new Product(name, id, price, quantity);
                    if(ProductDAOImpl.getInstance().update(product)==1){
                        System.out.println("Successfully updated the product.");
                    } else{
                        System.out.println("Could not find the product to update.");
                    }
                    
                    System.out.println("\n");
                    break;
                /**
                 * Exit
                 */
                case 'F':
                    System.out.println("Exit");
                    System.out.println("\n");
                    exitFlag = true;
                    break;
            }
        } while (!exitFlag);
    }
}
