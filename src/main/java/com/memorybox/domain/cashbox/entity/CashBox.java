package com.memorybox.domain.cashbox.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Table(name = "cash_box")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CashBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "account_num")
    private String accountNum;

    @Column(name = "balance")
    private int balance;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "core_bank_id")
    private long coreBankId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "maturity_enabled")
    private boolean maturityEnabled;

    @Column(name = "maturity_date")
    private LocalDate maturityDate;

    @Builder
    public CashBox(Long userId, String name, String description, String thumbnail, String accountNum, int balance, String productName, long coreBankId, LocalDateTime createdAt, LocalDate startDate, Boolean maturityEnabled, LocalDate maturityDate) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.accountNum = accountNum;
        this.balance = balance;
        this.productName = productName;
        this.coreBankId = coreBankId;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.maturityEnabled = maturityEnabled;
        this.maturityDate = maturityDate;
    }
}