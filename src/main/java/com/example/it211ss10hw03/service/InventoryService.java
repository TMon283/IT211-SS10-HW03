package com.example.it211ss10hw03.service;

import com.example.it211ss10hw03.model.entity.Product;

import java.util.List;

public interface InventoryService {
    Product importProduct(String sku, Long quantity, Long keeperId);
    Product exportProduct(String sku, Long quantity, Long keeperId);
    List<Product> lowStockProducts();
}

