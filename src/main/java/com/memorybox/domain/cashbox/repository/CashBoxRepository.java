package com.memorybox.domain.cashbox.repository;

import com.memorybox.domain.cashbox.entity.CashBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashBoxRepository extends JpaRepository<CashBox, Long> {
}
