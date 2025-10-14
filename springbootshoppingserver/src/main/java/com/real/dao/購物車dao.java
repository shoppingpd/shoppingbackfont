package com.real.dao;

import com.real.model.購物車;
import com.real.model.使用者;
import com.real.model.商品;
import com.real.dto.商品dto;
import com.real.dto.購物車dto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class 購物車dao {
	@PersistenceContext
	private EntityManager entityManager;

    // ===== 取得全部商品 =====
    @Transactional(readOnly = true)
    public List<購物車dto> getAll() {
        try {
            List<購物車> list = entityManager.createQuery("from 購物車", 購物車.class)
                    .getResultList();

            // 初始化 Lazy 關聯：使用者
            for (購物車 p : list) {
                Hibernate.initialize(p.get商品());
                Hibernate.initialize(p.get使用者());
            }
            

            // 轉 DTO
            return list.stream()
            	    .map(p -> new 購物車dto(
            	    		p.get購物車編號(),
        	            p.get商品().get商品名稱(),
        	            p.get商品().get商品圖片(),
        	            p.get使用者().get使用者編號(),   // Integer，直接取
        	            p.get商品().get商品編號(),       // Integer，直接取
        	            p.get商品大小(), 
        	            p.get商品顏色(), 
        	            p.get商品().get價格().intValue(),
        	            p.get數量(),
        	            p.get加入時間().toString()
            	    ))
            	    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getAll error: " + e.getMessage());
            return List.of(); // 回傳空列表比 null 更安全
        }
    }

	@Transactional
	public boolean add(購物車dto cartDto) {
		try {
			購物車 cart = new 購物車();
			cart.set數量(cartDto.get數量());
			cart.set加入時間(new Date());
			cart.set商品大小(cartDto.get商品大小());
			cart.set商品顏色(cartDto.get商品顏色());
			
			
			// ===== 建立使用者 =====
            if (cartDto.get使用者編號() != null) {
                使用者 user = entityManager.find(使用者.class, Integer.valueOf(cartDto.get使用者編號()));
                if (user == null) {
                    throw new RuntimeException("使用者不存在");
                }
                cart.set使用者(user); // 注意這裡的 setter 名稱
            } else {
                throw new RuntimeException("使用者編號不可為空");
            }
            // ===== 建立商品 =====
            if (cartDto.get商品編號() != null) {
				商品 prod = entityManager.find(商品.class, Integer.valueOf(cartDto.get商品編號()));
				if (prod == null) {
					throw new RuntimeException("商品不存在");
				}
				cart.set商品(prod); // 注意這裡的 setter 名稱
			} else {
				throw new RuntimeException("商品編號不可為空");
			}

            // ===== 上架時間處理 =====
            if (cartDto.get加入時間() != null) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(cartDto.get加入時間());
                } catch (Exception e) {
                	cartDto.set加入時間(cartDto.get加入時間()); // fallback：格式錯誤時用現在時間
                }
            } else {
            	cartDto.set加入時間(new Date().toString());
            }
            
			entityManager.persist(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	 @Transactional(readOnly = true)
    public 購物車dto findByid(int sid) {
        try {
        	購物車 p = entityManager.find(購物車.class, sid);
            if (p != null) 
            	Hibernate.initialize(p.get使用者());
            Hibernate.initialize(p.get商品());

            if (p == null) return null;

            return new 購物車dto(
            		p.get購物車編號(),
    	            p.get商品().get商品名稱(),
    	            p.get商品().get商品圖片(),
    	            p.get使用者().get使用者編號(),   // Integer，直接取
    	            p.get商品().get商品編號(),       // Integer，直接取
    	            p.get商品大小(), 
    	            p.get商品顏色(), 
    	            	p.get商品().get價格().intValue(),
    	            p.get數量(),
    	            p.get加入時間().toString()
            );
            
            
        } catch (Exception e) {
            System.out.println("findBySid error: " + e.getMessage());
            return null;
        }
    }
	 //根據使用者產生購物車資訊
	 @Transactional(readOnly = true)
	 public List<購物車dto> findByuserid(int sid) { 
		    try {
		        List<購物車> carts = entityManager.createQuery(
		                "SELECT c FROM 購物車 c WHERE c.使用者.使用者編號 = :uid", 購物車.class)
		                .setParameter("uid", sid)
		                .getResultList();

		        List<購物車dto> dtoList = new ArrayList<購物車dto>();


		        for (購物車 p : carts) {
		            Hibernate.initialize(p.get使用者());
		            Hibernate.initialize(p.get商品());

		            dtoList.add(new 購物車dto(
		                    p.get購物車編號(),
		                    p.get商品().get商品名稱(),
		                    p.get商品().get商品圖片(),
		                    p.get使用者().get使用者編號(),   
		                    p.get商品().get商品編號(),       
		                    p.get商品大小(), 
		                    p.get商品顏色(), 
		                    p.get商品().get價格().intValue(),
		                    p.get數量(),
		                    p.get加入時間().toString()
		            ));
		        }

		        return dtoList;

		    } catch (Exception e) {
		        System.out.println("findBySid error: " + e.getMessage());
		        return null;
		    }
		}


	 @Transactional
	    public boolean update(int sid, 購物車dto obj){
	        try {
	        	購物車 existing = entityManager.find(購物車.class, sid);
	            if (existing == null) return false;
	            if (obj.get使用者編號() != null) {
					使用者 user = entityManager.find(使用者.class, Integer.valueOf(obj.get使用者編號()));
					if (user == null) {
						throw new RuntimeException("使用者不存在");
					}
					existing.set使用者(user); // 注意這裡的 setter 名稱
				}
	            if (obj.get商品編號() != null) {
	            	商品 prod = entityManager.find(商品.class, Integer.valueOf(obj.get商品編號()));
					if (prod == null) {
						throw new RuntimeException("商品不存在");
					}
					existing.set商品(prod); // 注意這裡的 setter 名稱
				}
	            // 更新欄位
	            existing.set商品大小(obj.get商品大小());
	            existing.set商品顏色(obj.get商品顏色());
	            existing.set數量(obj.get數量());
	            existing.set加入時間(obj.get加入時間().toString() != null ? new Date() : existing.get加入時間());
	            
	            entityManager.merge(existing);
	            return true;
	        } catch (Exception e) {
	            System.out.println("update error: " + e.getMessage());
	            return false;
	        }
	 }
	 
	// ===== 刪除商品 =====
	    @Transactional
	    public boolean delete(int sid) {
	        try {
	        	購物車 existing = entityManager.find(	購物車.class, sid);
	            if (existing == null) return false;

	            entityManager.remove(existing);
	            return true;
	        } catch (Exception e) {
	            System.out.println("delete error: " + e.getMessage());
	            return false;
	        }
	    }
}
