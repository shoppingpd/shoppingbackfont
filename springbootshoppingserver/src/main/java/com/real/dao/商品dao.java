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
                Hibernate.initialize(p.get使用者());
            }

            // 轉 DTO
            return list.stream()
                    .map(p -> new 商品dto(
                            p.get商品編號(),
                            p.get商品名稱(),
                            p.get商品圖片(),
                            p.get價格(),
                            p.get使用者() != null ? p.get使用者().get帳號() : null
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getAll error: " + e.getMessage());
            return List.of(); // 回傳空列表比 null 更安全
        }
    }

    // ===== 新增商品 =====
    @Transactional
    public boolean add(商品 obj) {
        try {
            entityManager.persist(obj);
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
            if (p != null) Hibernate.initialize(p.get使用者());

            if (p == null) return null;

            return new 商品dto(
                    p.get商品編號(),
                    p.get商品名稱(),
                    p.get商品圖片(),
                    p.get價格(),
                    p.get使用者() != null ? p.get使用者().get帳號() : null
            );
        } catch (Exception e) {
            System.out.println("findBySid error: " + e.getMessage());
            return null;
        }
    }

    // ===== 更新商品 =====
    @Transactional
    public boolean update(int sid, 商品 obj) {
        try {
            商品 existing = entityManager.find(商品.class, sid);
            if (existing == null) return false;

            // 更新欄位
            existing.set商品名稱(obj.get商品名稱());
            existing.set價格(obj.get價格());
            existing.set使用者(obj.get使用者());

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
