
package com.real.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class 訂單dto {
	
	private Integer 訂單編號;
	private Integer 使用者編號;
	private Integer 購物車編號;
	private String 配送地址;
	private BigDecimal 總金額;
	private String 狀態;
	private String 建立時間;
	

}
