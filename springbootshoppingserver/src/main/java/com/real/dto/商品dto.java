package com.real.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class 商品dto {
    private Integer 商品編號;
    private String 商品名稱;
    private String 商品圖片;
    private String 商品描述;
    private String 顏色總類;
    	private String 尺寸總類;
    private BigDecimal 價格;
    private Integer 庫存數量;
    private String 上架者編號;
    	private String 上架者時間;
    // 只放需要的使用者資訊
}
