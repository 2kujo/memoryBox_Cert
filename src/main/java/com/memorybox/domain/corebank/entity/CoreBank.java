package com.memorybox.domain.corebank.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class CoreBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    private long userId;

    private String accountNum;

    private int balance;

    private String productName;

    private LocalDate startDate;

    private LocalDate maturityDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public CoreBank(Long id, String accountNum, int balance, String productName,
                    LocalDate startDate, LocalDate maturityDate, LocalDateTime createdAt, long userId){
        this.id = id;
        this.userId = userId;
        this.accountNum = accountNum;
        this.balance = balance;
        this.productName = productName;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.createdAt = createdAt;
    }

    public int updateBalance(int money){
        balance += money;
        return balance;
    }
}
