package com.real.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "使用者", catalog = "shopping", uniqueConstraints = {
        @UniqueConstraint(columnNames = "帳號"),
        @UniqueConstraint(columnNames = "電子郵件")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class 使用者 implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "使用者編號", unique = true, nullable = false)
    private Integer 使用者編號;
    
    @Column(name = "姓名", unique = true, nullable = false, length = 50)
    private String 姓名;
    
    @Column(name = "性別", unique = true, nullable = false, length = 50)
    private String 性別;
    
    @Column(name = "年齡", unique = true, nullable = false, length = 50)
    private String 年齡;
    
    @Column(name = "地址", unique = true, nullable = false, length = 50)
    private String 地址;

    @Column(name = "帳號", unique = true, nullable = false, length = 50)
    private String 帳號;

    @Column(name = "密碼", nullable = false)
    private String 密碼;

    @Column(name = "電子郵件", unique = true, length = 100)
    private String 電子郵件;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "建立時間", length = 19)
    private Date 建立時間;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "使用者")
    private Set<訂單> 訂單s = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "使用者")
    private Set<評論> 評論s = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "使用者")
    private Set<購物車> 購物車s = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "上架者")
    private Set<商品> 商品s = new HashSet<>();

	@Override
	public String toString() {
		return "使用者 [使用者編號=" + 使用者編號 + ", 帳號=" + 帳號 + ", 密碼=" + 密碼 + ", 電子郵件=" + 電子郵件 + ", 建立時間=" + 建立時間 + ", 訂單s="
				+ 訂單s + ", 評論s=" + 評論s + ", 購物車s=" + 購物車s + ", 商品s=" + 商品s + "]";
	}
    
    
}
