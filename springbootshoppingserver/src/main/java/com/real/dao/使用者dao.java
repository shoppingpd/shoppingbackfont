package com.real.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.real.dto.使用者dto;
import com.real.dto.商品dto;
import com.real.model.使用者;
import com.real.model.商品;
import com.real.model.訂單;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class 使用者dao {
	
	@PersistenceContext
    private EntityManager entityManager;
	// ===== 取得全部使用者 =====
    @Transactional(readOnly = true)
    public List<使用者dto> getAll() {
        try {
			List<使用者> list = entityManager.createQuery("SELECT u FROM 使用者 u", 使用者.class)
                    .getResultList();

            // 初始化 Lazy 關聯：使用者
//			for (使用者 u : list) {
//			    Optional.ofNullable(u.get購物車s()).ifPresent(Hibernate::initialize);
//			    Optional.ofNullable(u.get訂單s()).ifPresent(Hibernate::initialize);
//			    Optional.ofNullable(u.get評論s()).ifPresent(Hibernate::initialize);
//			    Optional.ofNullable(u.get商品s()).ifPresent(Hibernate::initialize);
//			}

            // 轉 DTO
            return list.stream()
            	    .map(p -> new 使用者dto(
            	    	p.get使用者編號(),
            	    	p.get姓名(),
            	    	p.get性別(),
            	    	p.get年齡(),
            	    	p.get地址(),
	    	        p.get帳號(),
	    	        p.get密碼(),
	    	        p.get電子郵件(),
	    	        p.get建立時間() != null ? p.get建立時間().toString() : null
            	    ))
            	    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getAll error: " + e.getMessage());
            return List.of(); // 回傳空列表比 null 更安全
        }
    }

    // ===== 新增使用者 =====
    @Transactional
    public boolean add(使用者dto obj) {
        try {
        	使用者 product = new 使用者();

            // ===== 將 DTO 的資料轉回實體 =====
        		product.set帳號(obj.get帳號());
        		product.set密碼(obj.get密碼());
        		product.set姓名(obj.get姓名());
        		product.set性別(obj.get性別());
        		product.set年齡(obj.get年齡());
        		product.set地址(obj.get地址());
        		product.set電子郵件(obj.get電子郵件());
        					// ===== 建立時間處理 =====
        		if (obj.get建立時間() != null) {
        			try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = sdf.parse(obj.get建立時間());
						product.set建立時間(date);
					} catch (Exception e) {
						product.set建立時間(new Date()); // fallback：格式錯誤時用現在時間
					}
				} else {
					product.set建立時間(new Date());
        		}

            // ✅ 存實體，不是 DTO
            entityManager.persist(product);

            return true;
        } catch (Exception e) {
            System.out.println("add error: " + e.getMessage());
            return false;
        }
    }



    // ===== 依使用者編號查找 =====
    @Transactional(readOnly = true)
    public 使用者dto findBySid(int sid) {
        try {
        	使用者 p = entityManager.find(使用者.class, sid);
        	
            if (p == null) return null;

            return new 使用者dto(
            		p.get使用者編號(),
				p.get姓名(),
				p.get性別(),
				p.get年齡(),
				p.get地址(),
	    	        p.get帳號(),
	    	        p.get密碼(),
	    	        p.get電子郵件(),
	    	        p.get建立時間() != null ? p.get建立時間().toString() : null
            );
            
            
        } catch (Exception e) {
            System.out.println("findBySid error: " + e.getMessage());
            return null;
        }
    }

    // ===== 更新使用者 只能更改密碼 姓名 電話 收件地址 =====
    @Transactional
    public boolean update(int sid, 使用者dto obj) {
        try {
        	使用者 existing = entityManager.find(使用者.class, sid);
            if (existing == null) return false;
            // 更新欄位
            existing.set密碼(obj.get密碼());
            existing.set姓名(obj.get姓名());
            existing.set性別(obj.get性別());
            existing.set年齡(obj.get年齡());
            existing.set地址(obj.get地址());
            
            

            entityManager.merge(existing);
            return true;
        } catch (Exception e) {
            System.out.println("update error: " + e.getMessage());
            return false;
        }
    }

    // ===== 刪除使用者=====
    @Transactional
    public boolean delete(int sid) {
        try {
        	使用者 existing = entityManager.find(使用者.class, sid);
            if (existing == null) return false;

            entityManager.remove(existing);
            return true;
        } catch (Exception e) {
            System.out.println("delete error: " + e.getMessage());
            return false;
        }
    }
	
}
