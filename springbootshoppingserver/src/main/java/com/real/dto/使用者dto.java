package com.real.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class 使用者dto {
    private Integer 使用者編號;
    	private String 姓名;
    	private String 性別;
    	private String 年齡;
    	private String 地址;
    private String 帳號;
    private String 密碼;
    private String 電子郵件;
    	private String 建立時間;
}
