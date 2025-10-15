package com.real.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class 購物車dto {
	private Integer 購物車編號;
    private String 商品名稱;
    private String 商品照片;
    private Integer 使用者編號; // 如果 DB 是 int
    private Integer 商品編號;    // 如果 DB 是 int
    private String 商品大小;
    private String 商品顏色;
    private Integer 價格;
    private Integer 數量;
    private Integer 狀態;
    private String 加入時間;
}
