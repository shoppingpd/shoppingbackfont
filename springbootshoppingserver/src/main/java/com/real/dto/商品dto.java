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
    private BigDecimal 價格;
    private String 上架者名稱; // 只放需要的使用者資訊
}
