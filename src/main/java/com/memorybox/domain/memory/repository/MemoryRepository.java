package com.memorybox.domain.memory.repository;

import com.memorybox.domain.memory.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
}
