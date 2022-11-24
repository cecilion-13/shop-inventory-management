package com.harsh.inventoryManagement.shopinventorymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.harsh.inventoryManagement.shopinventorymanagement.model.Product;
import com.harsh.inventoryManagement.shopinventorymanagement.repository.ShopItemRepository;

@Service
public class ShopInventoryService {
	
	@Autowired
	private ShopItemRepository repository;
	
	public List<Product> getAllItems(){
		return repository.findAll();
	}
	
	public Product getItemById(long id) {
		Optional<Product> product = repository.findById(id);
		if(product.isEmpty()) {
			throw new RuntimeException("No Product with this " + id +" is present");
		}
		return product.get();
	}
	
	public List<Product> getProductByCategory(@PathVariable String category) {
		List<Product> products = repository.findByCategory(category);
		if(products.isEmpty()) {
			throw new RuntimeException("No Product for this " + category);
		}
		return products;
	}
	
	public void createItem(Product product) {
		repository.save(product);
	}
	
	public Product updateItem(Long id, Product product) {
		Product updateProduct = repository.findById(id).get();
		updateProduct.setName(product.getName());
		updateProduct.setPrice(product.getPrice());
		updateProduct.setCategory(product.getCategory());
		
		repository.save(updateProduct);
		return updateProduct;
	}
	
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	

}
