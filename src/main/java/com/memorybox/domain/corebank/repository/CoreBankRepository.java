package com.memorybox.domain.corebank.repository;

import com.memorybox.domain.corebank.entity.CoreBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoreBankRepository extends JpaRepository<CoreBank, Long> {
}
