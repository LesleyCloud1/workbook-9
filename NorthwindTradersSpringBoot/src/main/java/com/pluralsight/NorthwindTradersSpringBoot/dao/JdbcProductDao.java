package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component // Registers this class as a Spring-managed component
public class JdbcProductDao implements ProductDao {
    private final DataSource dataSource;

    // Constructor for Spring to inject the DataSource
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Inserts a new product into the database
    @Override
    public void add(Product product) {
        String sql = "INSERT INTO Products (ProductName, CategoryID, UnitPrice) VALUES (?, ?, ?)";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getPrice());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding product to database:");
            e.printStackTrace();
        }
    }

    // Retrieves all products from the database
    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getDouble("UnitPrice")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products from database:");
            e.printStackTrace();
        }

        return products;
    }

    // Deletes a product from the database by its ID
    @Override
    public void delete(int productId) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product:");
            e.printStackTrace();
        }
    }

    // Updates an existing product
    @Override
    public void update(Product product) {
        String sql = "UPDATE Products SET ProductName = ?, CategoryID = ?, UnitPrice = ? WHERE ProductID = ?";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getProductId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating product:");
            e.printStackTrace();
        }
    }
}
