package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OnlineStore {
    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        loadProducts();

        System.out.println("Loaded " + products.size() + " products");
    }

    private static void loadProducts() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.csv"));

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String sku = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String department = parts[3];


                products.add(new Product(sku, name, price, department));


            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
