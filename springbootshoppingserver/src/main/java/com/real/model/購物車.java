package com.real.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "購物車", catalog = "shopping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 購物車 implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "購物車編號", unique = true, nullable = false)
    private Integer 購物車編號;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "使用者編號", nullable = false)
    private 使用者 使用者;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "商品編號", nullable = false)
    private 商品 商品;

    @Column(name = "數量", nullable = false)
    private int 數量;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "加入時間", length = 19)
    private Date 加入時間;
}
