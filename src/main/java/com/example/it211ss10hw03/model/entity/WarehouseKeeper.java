package com.example.it211ss10hw03.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warehouse_keeper")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WarehouseKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String staffCode;
}