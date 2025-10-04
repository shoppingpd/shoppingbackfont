package com.real.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "訂單明細", catalog = "shopping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 訂單明細 implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "明細編號", unique = true, nullable = false)
    private Integer 明細編號;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "商品編號", nullable = false)
    private 商品 商品;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "訂單編號", nullable = false)
    private 訂單 訂單;

    @Column(name = "數量", nullable = false)
    private int 數量;

    @Column(name = "單價", nullable = false, precision = 10)
    private BigDecimal 單價;

	@Override
	public String toString() {
		return "訂單明細 [明細編號=" + 明細編號 + ", 商品=" + 商品 + ", 訂單=" + 訂單 + ", 數量=" + 數量 + ", 單價=" + 單價 + "]";
	}
    
}
