package com.real.dao;

import com.real.model.商品;
import com.real.model.使用者;
import com.real.dto.商品dto;
import com.real.dto.商品dto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class 商品dao {

    @PersistenceContext
    private EntityManager entityManager;

    // ===== 取得全部商品 =====
    @Transactional(readOnly = true)
    public List<商品dto> getAll() {
        try {
            List<商品> list = entityManager.createQuery("from 商品", 商品.class)
                    .getResultList();

            // 初始化 Lazy 關聯：使用者
            for (商品 p : list) {
                Hibernate.initialize(p.get上架者());
            }

            // 轉 DTO
            return list.stream()
            	    .map(p -> new 商品dto(
            	    		 p.get商品編號(),
            	    	        p.get商品名稱(),
            	    	        p.get商品圖片(),
            	    	        p.get商品描述(),
            	    	        p.get顏色總類(),
            	    	        p.get尺寸總類(),
            	    	        p.get價格(),
            	    	        p.get庫存數量(),
            	    	        p.get上架者() != null ? p.get上架者().get使用者編號().toString() : null,
            	    	        p.get上架時間() != null ? p.get上架時間().toString() : null
            	    ))
            	    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getAll error: " + e.getMessage());
            return List.of(); // 回傳空列表比 null 更安全
        }
    }
    
 // 分頁方法
    @Transactional(readOnly = true)
    public List<商品dto> getPage(int pageNo, int pageSize, String sortBy) {
        try {
            int offset = pageNo * pageSize; // 計算起始位置
            List<商品> list = entityManager.createQuery("from 商品 order by " + sortBy, 商品.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();

            // 初始化 Lazy 關聯：使用者
            for (商品 p : list) {
                Hibernate.initialize(p.get上架者());
            }

            // 轉 DTO
            return list.stream()
                    .map(p -> new 商品dto(
                            p.get商品編號(),
                            p.get商品名稱(),
                            p.get商品圖片(),
                            p.get商品描述(),
                            p.get顏色總類(),
                            p.get尺寸總類(),
                            p.get價格(),
                            p.get庫存數量(),
                            p.get上架者() != null ? p.get上架者().get使用者編號().toString() : null,
                            p.get上架時間() != null ? p.get上架時間().toString() : null
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getPage error: " + e.getMessage());
            return List.of();
        }
    }
	 // 查總數量
	    public int getTotalCount() {
	        Long count = entityManager.createQuery("SELECT COUNT(p) FROM 商品 p", Long.class)
	                                  .getSingleResult();
	        return count.intValue();
	    }
	// 取得最大頁數
    public int findMaxPage() {
        int total = getTotalCount();
        return (int) Math.ceil((double) total / 6);
    }
    // ===== 新增商品 =====
    @Transactional
    public boolean add(商品dto obj) {
        try {
            商品 product = new 商品();

            // ===== 將 DTO 的資料轉回實體 =====
            product.set商品名稱(obj.get商品名稱());
            product.set商品圖片(obj.get商品圖片());
            product.set商品描述(obj.get商品描述());
            product.set顏色總類(obj.get顏色總類());
            product.set尺寸總類(obj.get尺寸總類());
            product.set價格(obj.get價格());
            product.set庫存數量(obj.get庫存數量());

            // ===== 建立上架者 =====
            if (obj.get上架者編號() != null) {
                使用者 user = entityManager.find(使用者.class, Integer.valueOf(obj.get上架者編號()));
                if (user == null) {
                    throw new RuntimeException("上架者不存在");
                }
                product.set上架者(user); // 注意這裡的 setter 名稱
            } else {
                throw new RuntimeException("上架者編號不可為空");
            }

            // ===== 上架時間處理 =====
            if (obj.get上架時間() != null) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(obj.get上架時間());
                    product.set上架時間(date);
                } catch (Exception e) {
                    product.set上架時間(new Date()); // fallback：格式錯誤時用現在時間
                }
            } else {
                product.set上架時間(new Date());
            }

            // ✅ 存實體，不是 DTO
            entityManager.persist(product);

            return true;
        } catch (Exception e) {
            System.out.println("add error: " + e.getMessage());
            return false;
        }
    }



    // ===== 依商品編號查找 =====
    @Transactional(readOnly = true)
    public 商品dto findBySid(int sid) {
        try {
            商品 p = entityManager.find(商品.class, sid);
            if (p != null) Hibernate.initialize(p.get上架者());

            if (p == null) return null;

            return new 商品dto(
            		p.get商品編號(),
        	        p.get商品名稱(),
        	        p.get商品圖片(),
        	        p.get商品描述(),
        	        p.get顏色總類(),
        	        p.get尺寸總類(),
        	        p.get價格(),
        	        p.get庫存數量(),
        	        p.get上架者() != null ? p.get上架者().get使用者編號().toString() : null,
        	        p.get上架時間() != null ? p.get上架時間().toString() : null
            );
            
            
        } catch (Exception e) {
            System.out.println("findBySid error: " + e.getMessage());
            return null;
        }
    }

    // ===== 更新商品 =====
    @Transactional
    public boolean update(int sid, 商品dto obj) {
        try {
            商品 existing = entityManager.find(商品.class, sid);
            if (existing == null) return false;
            if (obj.get上架者編號() != null) {
				使用者 user = entityManager.find(使用者.class, Integer.valueOf(obj.get上架者編號()));
				if (user == null) {
					throw new RuntimeException("上架者不存在");
				}
				existing.set上架者(user); // 注意這裡的 setter 名稱
			}
            // 更新欄位
            existing.set商品名稱(obj.get商品名稱());
            existing.set價格(obj.get價格());
            existing.set庫存數量(obj.get庫存數量());
            existing.set商品圖片(obj.get商品圖片());
            existing.set商品描述(obj.get商品描述());
            existing.set顏色總類(obj.get顏色總類());
            existing.set尺寸總類(obj.get尺寸總類());

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
            商品 existing = entityManager.find(商品.class, sid);
            if (existing == null) return false;

            entityManager.remove(existing);
            return true;
        } catch (Exception e) {
            System.out.println("delete error: " + e.getMessage());
            return false;
        }
    }
}
