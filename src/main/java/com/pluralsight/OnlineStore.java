package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Online Store
 * A command-line store that loads products from products.csv and lets
 * customers browse, search, and manage a shopping cart.
 */
public class OnlineStore {

    // All products the store sells, shared so every method can use it
    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        // Load the store's products from the file
        loadProducts();

        // Quick test: show how many products were loaded
        System.out.println("Loaded " + products.size() + " products");
    }

    /**
     * Reads products.csv and adds each product to the products list.
     * Each line looks like: SKU|Product Name|Price|Department
     */
    private static void loadProducts() {
        // Try: reading a file can fail (e.g. file not found)
        try {
            // Open products.csv for reading
            BufferedReader reader = new BufferedReader(new FileReader("products.csv"));

            // Skip the header line (SKU|Product Name|Price|Department)
            reader.readLine();

            String line;

            // Read one line at a time until readLine() returns null (end of file)
            while ((line = reader.readLine()) != null) {
                // Split the line on | into sku, name, price and department
                String[] parts = line.split("\\|");

                // SKU, name and department stay text; only the price is converted
                String sku = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String department = parts[3];

                // Create a Product and add it to the list
                products.add(new Product(sku, name, price, department));
            }

            // Close the file
            reader.close();
        } catch (IOException e) {
            // Runs if the file can not be found or read
            System.out.println("Error: " + e.getMessage());
        }
    }
}