package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.NorthwindTradersSpringBoot.dao.ProductDao;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component // Registers this class as a Spring component for CLI
public class NorthwindApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao; // Spring will inject an implementation

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Product Management CLI ===");
            System.out.println("1. List all products");
            System.out.println("2. Add a new product");
            System.out.println("3. Delete a product");
            System.out.println("4. Update a product");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listAllProducts();
                    break;
                case "2":
                    addNewProduct(scanner);
                    break;
                case "3":
                    deleteProduct(scanner);
                    break;
                case "4":
                    updateProduct(scanner);
                    break;
                case "0":
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void listAllProducts() {
        List<Product> products = productDao.getAll();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.printf("%-5s %-30s %-10s %-10s%n", "ID", "Name", "Category", "Price");
            for (Product p : products) {
                System.out.printf("%-5d %-30s %-10d $%-10.2f%n",
                        p.getProductId(), p.getName(), p.getCategoryId(), p.getPrice());
            }
        }
    }

    private void addNewProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category ID: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product product = new Product(0, name, categoryId, price);
        productDao.add(product);
        System.out.println("Product added successfully.");
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        productDao.delete(id);
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new category ID: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product updatedProduct = new Product(id, name, categoryId, price);
        productDao.update(updatedProduct);
    }
}
