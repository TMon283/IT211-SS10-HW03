package com.example.it211ss10hw03.repository;

import com.example.it211ss10hw03.model.entity.WarehouseKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseKeeperRepository extends JpaRepository<WarehouseKeeper, Long> {
}
