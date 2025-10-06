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
    
    @Column(name = "商品大小", nullable = false)
    private String 商品大小;
    
    @Column(name = "商品顏色", nullable = false)
    private String 商品顏色;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "加入時間", length = 19)
    private Date 加入時間;

	@Override
	public String toString() {
		return "購物車 [購物車編號=" + 購物車編號 + ", 使用者=" + 使用者 + ", 商品=" + 商品 + ", 數量=" + 數量 + ", 加入時間=" + 加入時間 + ", get購物車編號()="
				+ get購物車編號() + ", get使用者()=" + get使用者() + ", get商品()=" + get商品() + ", get數量()=" + get數量()
				+ ", get加入時間()=" + get加入時間() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
    
}
