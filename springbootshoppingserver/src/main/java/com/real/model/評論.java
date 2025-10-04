package com.real.model;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "評論", catalog = "shopping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 評論 implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "評論編號", unique = true, nullable = false)
    private Integer 評論編號;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "使用者編號", nullable = false)
    private 使用者 使用者;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "商品編號", nullable = false)
    private 商品 商品;

    @Column(name = "評分")
    private Integer 評分;

    @Column(name = "評論內容", length = 65535)
    private String 評論內容;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "建立時間", length = 19)
    private Date 建立時間;
}
