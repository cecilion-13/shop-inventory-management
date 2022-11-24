package com.harsh.inventoryManagement.shopinventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.inventoryManagement.shopinventorymanagement.model.Product;


@Repository
public interface ShopItemRepository extends JpaRepository<Product, Long>{

	public List<Product> findByCategory(String category);
}
