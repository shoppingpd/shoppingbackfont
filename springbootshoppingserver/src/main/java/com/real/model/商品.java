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
@Table(name = "商品", catalog = "shopping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 商品 implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "商品編號", unique = true, nullable = false)
    private Integer 商品編號;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "上架者編號", nullable = false)
    private 使用者 使用者;

    @Column(name = "商品名稱", nullable = false, length = 100)
    private String 商品名稱;

    @Column(name = "商品圖片", length = 500)
    private String 商品圖片;

    @Column(name = "商品描述", length = 65535)
    private String 商品描述;

    @Column(name = "顏色總類", nullable = false, length = 100)
    private String 顏色總類;

    @Column(name = "尺寸總類", nullable = false, length = 45)
    private String 尺寸總類;

    @Column(name = "價格", nullable = false, precision = 10)
    private BigDecimal 價格;

    @Column(name = "庫存數量")
    private Integer 庫存數量;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "上架時間", length = 19)
    private Date 上架時間;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "商品")
    private Set<評論> 評論s = new HashSet<>(); 	 	

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "商品")
    private Set<購物車> 購物車s = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "商品")
    private Set<訂單明細> 訂單明細s = new HashSet<>();
}
