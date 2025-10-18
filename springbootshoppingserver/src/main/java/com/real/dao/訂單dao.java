package com.real.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.real.dto.訂單dto;
import com.real.dto.購物車dto;
import com.real.dto.訂單dto;
import com.real.model.使用者;
import com.real.model.訂單;
import com.real.model.購物車;
import com.real.model.訂單;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class 訂單dao {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private 購物車dao cartDao;
	
	 // ===== 取得全部訂單 =====
    @Transactional(readOnly = true)
    public List<訂單dto> getAll() {
        try {
            List<訂單> list = entityManager.createQuery("from 訂單", 訂單.class)
                    .getResultList();

            // 初始化 Lazy 關聯：使用者
            for (訂單 p : list) {
                Hibernate.initialize(p.get使用者());
                Hibernate.initialize(p.get購物車());
            }

            // 轉 DTO
            return list.stream()
            	    .map(p -> new 訂單dto(
            	    		p.get訂單編號(),
            	    		p.get使用者().get使用者編號(),
            	    		p.get購物車().get購物車編號(),
            	    		p.get配送地址(),
            	    		p.get總金額(),
            	    		p.get狀態(),
            	    		p.get建立時間() != null ? p.get建立時間().toString() : null

            	    ))
            	    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getAll error: " + e.getMessage());
            return List.of(); // 回傳空列表比 null 更安全
        }
    }
    
	 @Transactional(readOnly = true)
	    public 訂單dto findByid(int sid) {
	        try {
	        	訂單 p = entityManager.find(訂單.class, sid);
	            if (p != null) 
	            	Hibernate.initialize(p.get使用者());
	            	Hibernate.initialize(p.get購物車());

	            if (p == null) return null;

	            return new 訂單dto(
	            		p.get訂單編號(),
        	    		p.get使用者().get使用者編號(),
        	    		p.get購物車().get購物車編號(),
        	    		p.get配送地址(),
        	    		p.get總金額(),
        	    		p.get狀態(),
        	    		p.get建立時間() != null ? p.get建立時間().toString() : null
	            );
	            
	            
	        } catch (Exception e) {
	            System.out.println("findBySid error: " + e.getMessage());
	            return null;
	        }
	    }
	 
	 //根據使用者產生訂單資訊
	 @Transactional(readOnly = true)
	 public List<訂單dto> findByuserid(int sid) { 
		    try {
		        List<訂單> carts = entityManager.createQuery(
		                "SELECT c FROM 訂單 c WHERE c.使用者.使用者編號 = :uid", 訂單.class)
		                .setParameter("uid", sid)
		                .getResultList();

		        List<訂單dto> dtoList = new ArrayList<訂單dto>();


		        for (訂單 p : carts) {
		            Hibernate.initialize(p.get使用者());
		            Hibernate.initialize(p.get購物車());

		            dtoList.add(new 訂單dto(
		            		p.get訂單編號(),
	        	    		p.get使用者().get使用者編號(),
	        	    		p.get購物車().get購物車編號(),
	        	    		p.get配送地址(),
	        	    		p.get總金額(),
	        	    		p.get狀態(),
	        	    		p.get建立時間() != null ? p.get建立時間().toString() : null
		            ));
		        }

		        return dtoList;

		    } catch (Exception e) {
		        System.out.println("findBySid error: " + e.getMessage());
		        return null;
		    }
		}
	 
	 
	//根據商品產生訂單資訊
	 @Transactional(readOnly = true)
	    public List<訂單dto> findByprodId(int sid) { 
	    	
	    	
	    		List<Integer> cartIds=cartDao.findByProductId(sid);
	    		for (Integer id : cartIds) {
	    		    System.out.println(id);
	    		}
	        // 1. 處理空列表：如果傳入的 ID 列表是空的，則直接返回空結果，避免 JPQL 錯誤
	        if (cartIds == null || cartIds.isEmpty()) {
	            return List.of();
	        }
	        
	        try {
	            // JPQL 查詢：使用 IN 語句，找到 訂單 c 關聯的 購物車 的 購物車編號 在 cartIds 列表中的所有訂單
	            List<訂單> orders = entityManager.createQuery(
	                    "SELECT c FROM 訂單 c WHERE c.購物車.購物車編號 IN :cartIds", 訂單.class)
	                    .setParameter("cartIds", cartIds) // 將列表設置為 IN 參數
	                    .getResultList();

	            List<訂單dto> dtoList = new ArrayList<>();

	            // 遍歷結果並轉換為 DTO
	            for (訂單 p : orders) {
	                // 手動初始化 Lazy Loading 的關聯
	                Hibernate.initialize(p.get使用者());
	                Hibernate.initialize(p.get購物車());

	                dtoList.add(new 訂單dto(
	                        p.get訂單編號(),
	                        p.get使用者().get使用者編號(),
	                        p.get購物車().get購物車編號(),
	                        p.get配送地址(),
	                        p.get總金額(),
	                        p.get狀態(),
	                        p.get建立時間() != null ? p.get建立時間().toString() : null
	                ));
	            }

	            return dtoList;

	        } catch (Exception e) {
	            System.out.println("findByCartIds error: " + e.getMessage());
	            return List.of(); // 回傳空列表
	        }
	    }
		 

 // 分頁方法
    @Transactional(readOnly = true)
    public List<訂單dto> getPage(int pageNo, int pageSize, String sortBy) {
        try {
            int offset = pageNo * pageSize; // 計算起始位置
            List<訂單> list = entityManager.createQuery("from 訂單 order by " + sortBy, 訂單.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();

            // 初始化 Lazy 關聯：使用者
            for (訂單 p : list) {
                Hibernate.initialize(p.get使用者());
                Hibernate.initialize(p.get購物車());
            }

            // 轉 DTO
            return list.stream()
                    .map(p -> new 訂單dto(
                    		p.get訂單編號(),
            	    		p.get使用者().get使用者編號(),
            	    		p.get購物車().get購物車編號(),
            	    		p.get配送地址(),
            	    		p.get總金額(),
            	    		p.get狀態(),
            	    		p.get建立時間() != null ? p.get建立時間().toString() : null
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getPage error: " + e.getMessage());
            return List.of();
        }
    }
	 // 查總數量
	    public int getTotalCount() {
	        Long count = entityManager.createQuery("SELECT COUNT(p) FROM 訂單 p", Long.class)
	                                  .getSingleResult();
	        return count.intValue();
	    }
	// 取得最大頁數
    public int findMaxPage() {
        int total = getTotalCount();
        return (int) Math.ceil((double) total / 6);
    }
    // ===== 新增訂單 =====
    @Transactional
    public Integer add(訂單dto obj) {
        try {
        	訂單 product = new 訂單();

            // ===== 將 DTO 的資料轉回實體 =====
        	product.set配送地址(obj.get配送地址());
        	product.set總金額(obj.get總金額());
        	product.set狀態(obj.get狀態());


            // ===== 建立上架者 =====
            if (obj.get使用者編號() != null) {
                使用者 user = entityManager.find(使用者.class, Integer.valueOf(obj.get使用者編號()));
                if (user == null) {
                    throw new RuntimeException("上架者不存在");
                }
                product.set使用者(user); // 注意這裡的 setter 名稱
            } else {
                throw new RuntimeException("上架者編號不可為空");
            }
            
            if (obj.get購物車編號() != null) {
            	購物車 cart = entityManager.find(購物車.class, Integer.valueOf(obj.get購物車編號()));
                if (cart == null) {
                    throw new RuntimeException("購物車不存在");
                }
                product.set購物車(cart); // 注意這裡的 setter 名稱
            } else {
                throw new RuntimeException("購物車編號不可為空");
            }

            // ===== 上架時間處理 =====
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
            entityManager.flush(); // 確保主鍵立刻產生

            System.out.println("✅ 新增訂單成功，ID = " + product.get訂單編號());
            return product.get訂單編號(); // 回傳剛產生的 ID
        } catch (Exception e) {
            System.out.println("add error: " + e.getMessage());
            return -1;
        }
    }



    // ===== 依訂單編號查找 =====
    @Transactional(readOnly = true)
    public 訂單dto findBySid(int sid) {
        try {
        	訂單 p = entityManager.find(訂單.class, sid);
            if (p != null) Hibernate.initialize(p.get使用者());

            if (p == null) return null;
            
            Hibernate.initialize(p.get購物車());


            return new 訂單dto(
            		p.get訂單編號(),
    	    		p.get使用者().get使用者編號(),
    	    		p.get購物車().get購物車編號(),
    	    		p.get配送地址(),
    	    		p.get總金額(),
    	    		p.get狀態(),
    	    		p.get建立時間() != null ? p.get建立時間().toString() : null
            );
            
            
        } catch (Exception e) {
            System.out.println("findBySid error: " + e.getMessage());
            return null;
        }
    }

    // ===== 更新訂單 =====
    @Transactional
    public boolean update(int sid, 訂單dto obj) {
        try {
        	訂單 existing = entityManager.find(訂單.class, sid);
            if (existing == null) return false;
            if (obj.get使用者編號() != null) {
				使用者 user = entityManager.find(使用者.class, Integer.valueOf(obj.get使用者編號()));
				if (user == null) {
					throw new RuntimeException("上架者不存在");
				}
				existing.set使用者(user); // 注意這裡的 setter 名稱
			}
            // 更新欄位

            existing.set配送地址(obj.get配送地址());
            existing.set總金額(obj.get總金額());
            existing.set狀態(obj.get狀態());

            entityManager.merge(existing);
            return true;
        } catch (Exception e) {
            System.out.println("update error: " + e.getMessage());
            return false;
        }
    }

    // ===== 刪除訂單 =====
    @Transactional
    public boolean delete(int sid) {
        try {
        	訂單 existing = entityManager.find(訂單.class, sid);
            if (existing == null) return false;

            entityManager.remove(existing);
            return true;
        } catch (Exception e) {
            System.out.println("delete error: " + e.getMessage());
            return false;
        }
    }
	
}
