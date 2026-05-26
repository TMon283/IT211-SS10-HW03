package com.example.it211ss10hw03.service.impl;

import com.example.it211ss10hw03.exception.BusinessException;
import com.example.it211ss10hw03.model.entity.Product;
import com.example.it211ss10hw03.model.entity.WarehouseKeeper;
import com.example.it211ss10hw03.repository.ProductRepository;
import com.example.it211ss10hw03.repository.WarehouseKeeperRepository;
import com.example.it211ss10hw03.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepo;
    private final WarehouseKeeperRepository keeperRepo;

    @Transactional
    @Override
    public Product importProduct(String sku, Long quantity, Long keeperId) {
        WarehouseKeeper keeper = keeperRepo.findById(keeperId)
                .orElseThrow(() -> new BusinessException("Không tìm thấy nhân viên kho"));

        Product product = productRepo.findBySku(sku)
                .orElseThrow(() -> new BusinessException("Không tìm thấy sản phẩm"));

        product.setQuantity(product.getQuantity() + quantity);
        productRepo.save(product);

        log.info("Nhập kho thành công bởi Keeper {}. SKU={}, Quantity={}", keeper.getStaffCode(), sku, quantity);
        return product;
    }

    @Transactional
    @Override
    public Product exportProduct(String sku, Long quantity, Long keeperId) {
        WarehouseKeeper keeper = keeperRepo.findById(keeperId)
                .orElseThrow(() -> new BusinessException("Không tìm thấy nhân viên kho"));

        int updated = productRepo.exportProduct(sku, quantity);
        if (updated == 0) {
            throw new IllegalArgumentException("Số lượng xuất hàng vượt quá tồn kho hiện tại!");
        }

        Product product = productRepo.findBySku(sku)
                .orElseThrow(() -> new BusinessException("Không tìm thấy sản phẩm"));

        log.info("Xuất kho thành công bởi Keeper {}. SKU={}, Quantity={}", keeper.getStaffCode(), sku, quantity);
        return product;
    }

    @Override
    public List<Product> lowStockProducts() {
        return productRepo.findAll().stream()
                .filter(p -> p.getQuantity() < 5)
                .collect(Collectors.toList());
    }
}
