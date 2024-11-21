package com.alimert.model;


import com.alimert.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "account_no")
    private String accountNo;

    private String iban;

    private BigDecimal amount;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING) // for enum types
    private CurrencyType currencyType;


}
