package com.example.repository;

import com.example.repository.model.Product;
import com.example.repository.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RepositoryApplication implements CommandLineRunner {

	private ProductRepository productRepository;

	private Logger LOG = LoggerFactory.getLogger(RepositoryApplication.class);

	@Autowired
	public void productRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RepositoryApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Product product1 = new Product();
		product1.setName("Tester Product");
		product1.setDescription("This is a tester product");
		product1.setCategory("TEST");
		product1.setType("GENERAL");
		product1.setPrice(0.0);

		// Save product1 to H2
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setName("Another Tester Product");
		product2.setDescription("This is a tester product");
		product2.setCategory("TEST");
		product2.setType("CUSTOM");
		product2.setPrice(15.0);

		// Save product2 to H2
		productRepository.save(product2);

		Product product3 = new Product();
		product3.setName("Tester Product");
		product3.setDescription("description");
		product3.setCategory("TEST");
		product3.setType("SPECIFIC");
		product3.setPrice(19.0);

		// Save product3 to H2
		productRepository.save(product3);

		// Delete product3 from H2
		productRepository.delete(product3);

		// Find "GENERAL" type of products from H2
		Product foundProduct = productRepository.findByType("GENERAL");

		if (foundProduct != null) {
			// If product found then delete it
			LOG.info("Product count in database:" + productRepository.count());
			productRepository.delete(foundProduct);
			LOG.info("Product is deleted");
			LOG.info("Product count in database:" + productRepository.count());
		}

		// Find "SPECIFIC" type of products
		Product productToUpdate = productRepository.findByType("SPECIFIC");

		if (productToUpdate != null) {
			LOG.info("Before update product details:" + productToUpdate);
			productToUpdate.setName("Updated Product");
			productToUpdate.setDescription("Updated description");

			Product updated = productRepository.save(productToUpdate);
			LOG.info("Updated product details:" + updated.toString());
		}

		// Find all products from H2
		List<Product> products = productRepository.findAll();

		// Print out all products to console
		for(Product product : products) {
			LOG.info("Products found:" + product.toString());
		}

		// Find "CUSTOM" type of products
		Product resultProduct = productRepository.findByType("CUSTOM");

		if (resultProduct != null) {
			LOG.info("GENERAL type of product found:" + resultProduct.toString());
		}

		// Find all products with given description and category
		List<Product> results = productRepository.findByDescriptionAndCategory("This is a tester product", "TEST");

		for (Product product : results) {
			LOG.info("Matching results are:" + product.toString());
		}

		List<String> names = new ArrayList<>();
		names.add("Tester Product");
		names.add("Another Tester Product");

		// Find all products with given category and within a list of names
		List<Product> resultProducts = productRepository.findByCategoryAndNameIn("TEST", names);

		for (Product product : resultProducts) {
			LOG.info("Matching results for findByCategoryAndNameIn:" + product.toString());
		}
	}
}
