package com.harsh.inventoryManagement.shopinventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.inventoryManagement.shopinventorymanagement.model.Product;
import com.harsh.inventoryManagement.shopinventorymanagement.service.ShopInventoryService;

@RestController
public class InventoryController {

	@Autowired
	private ShopInventoryService service;

	@GetMapping("/shop-items")
	public List<Product> getAllItems() {
		return service.getAllItems();
	}

	@GetMapping("/shop-items/{id}")
	public Product getProductById(@PathVariable Long id) {
		return service.getItemById(id);
	}

	@GetMapping("/shop-items/{category}")
	public List<Product> getProductByCategory(@PathVariable String category) {
		return service.getProductByCategory(category);
	}

	@PostMapping("/shop-items")
	public void createItem(@RequestBody Product product) {
		service.createItem(product);
	}

	@PutMapping("/shop-items/{id}")
	public Product updateItem(@PathVariable long id, @RequestBody Product product) {
		Product updateProduct = service.getItemById(id);
		if (updateProduct == null) {
			throw new RuntimeException("Product with this " + id + " is not present");
		}

		updateProduct = service.updateItem(id, product);
		return updateProduct;
	}
	
	@DeleteMapping("/shop-items/{id}")
	public void deleteItem(@PathVariable Long id) {
		Product product = service.getItemById(id);
		if (product == null) {
			throw new RuntimeException("Product with this " + id + " is not present");
		}
		service.delete(id);
	}

}
