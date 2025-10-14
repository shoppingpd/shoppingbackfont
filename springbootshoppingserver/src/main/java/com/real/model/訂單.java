package com.real.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "訂單", catalog = "shopping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 訂單 implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "訂單編號", unique = true, nullable = false)
    private Integer 訂單編號;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "使用者編號", nullable = false)
    private 使用者 使用者;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "購物車編號", nullable = false)
    private 購物車 購物車;
    
    @Column(name = "配送地址", nullable = false, precision = 10)
    private String  配送地址;

    @Column(name = "總金額", nullable = false, precision = 10)
    private BigDecimal 總金額;

    @Column(name = "狀態", length = 3)
    private String 狀態;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "建立時間", length = 19)
    private Date 建立時間;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "訂單")
    private Set<訂單明細> 訂單明細s = new HashSet<>();
}
