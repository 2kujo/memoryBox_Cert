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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CashBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String thumbnail;

    @Column
    private String accountNum;

    @Column
    int balance;

    @Column
    private String productName;

    @Column
    private long coreBankId;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDate startDate;

    @Column
    private Boolean maturityEnabled;

    @Column
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