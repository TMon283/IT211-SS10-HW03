package com.example.it211ss10hw03.controller;

import com.example.it211ss10hw03.model.dto.request.ExportRequest;
import com.example.it211ss10hw03.model.dto.request.ImportRequest;
import com.example.it211ss10hw03.model.dto.response.ApiDataResponse;
import com.example.it211ss10hw03.model.entity.Product;
import com.example.it211ss10hw03.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/import")
    public ResponseEntity<ApiDataResponse<Product>> importProduct(@RequestBody ImportRequest request) {
        Product product = inventoryService.importProduct(request.getSku(), request.getQuantity(), request.getKeeperId());
        return ResponseEntity.ok(ApiDataResponse.<Product>builder()
                .success(true)
                .message("Nhập kho thành công")
                .data(product)
                .status(HttpStatus.OK).build());
    }

    @PostMapping("/export")
    public ResponseEntity<ApiDataResponse<Product>> exportProduct(@RequestBody ExportRequest request) {
        Product product = inventoryService.exportProduct(request.getSku(), request.getQuantity(), request.getKeeperId());
        return ResponseEntity.ok(ApiDataResponse.<Product>builder()
                .success(true)
                .message("Xuất kho thành công")
                .data(product)
                .status(HttpStatus.OK).build());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<ApiDataResponse<List<Product>>> lowStock() {
        List<Product> products = inventoryService.lowStockProducts();
        return ResponseEntity.ok(ApiDataResponse.<List<Product>>builder()
                .success(true)
                .message("Danh sách sản phẩm tồn kho thấp")
                .data(products)
                .status(HttpStatus.OK).build());
    }
}

