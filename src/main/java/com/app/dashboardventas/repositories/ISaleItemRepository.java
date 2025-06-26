package com.app.dashboardventas.repositories;

import com.app.dashboardventas.models.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleItemRepository extends JpaRepository<SaleItem,Long> {
}
