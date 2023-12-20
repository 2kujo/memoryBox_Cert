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
@Table(name = "core_bank")
@Entity
public class CoreBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "account_num")
    private String accountNum;

    @Column(name = "balance")
    private int balance;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "maturity_enabled")
    private boolean maturityEnabled;

    @Builder
    public CoreBank(Long id, String accountNum, int balance, String productName, LocalDate startDate,
                    LocalDate maturityDate, LocalDateTime createdAt, long userId, boolean maturityEnabled){
        this.id = id;
        this.userId = userId;
        this.accountNum = accountNum;
        this.balance = balance;
        this.productName = productName;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.createdAt = createdAt;
        this.maturityEnabled = maturityEnabled;
    }

    public int updateBalance(int money){
        balance += money;
        return balance;
    }
}
