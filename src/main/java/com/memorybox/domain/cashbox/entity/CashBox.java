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

    @Column
    private long coreBankId;

    @Column
    private long userId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String thumbnail;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @Column
    private String accountNum;

    @Column
    private int balance;

    @Column
    private String productName;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate maturityDate;

    @Column
    private boolean maturityEnabled;

    @Column(name = "checked_maturity_read")
    private boolean checkedMaturityRead;

    @Builder
    public CashBox(long userId, String name, String description, String thumbnail, LocalDateTime createdAt, long coreBankId, String accountNum, int balance, String productName, LocalDate startDate, LocalDate maturityDate, boolean maturityEnabled, boolean checkedMaturityRead) {
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
        this.checkedMaturityRead = checkedMaturityRead;
    }
}