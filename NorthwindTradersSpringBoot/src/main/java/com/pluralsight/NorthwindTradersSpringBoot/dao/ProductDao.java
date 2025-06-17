package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import java.util.List;

/**
 * ProductDao defines the interface (contract) for interacting with Product data.
 */
public interface ProductDao {
    void add(Product product);//Add a new product
    List<Product> getAll();//Retrieve all products
    void delete(int productId);
    void update(Product product);

}
