package com.real.myinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.real.model.商品;

@Repository
public interface 商品Repository extends JpaRepository<商品, Integer> {
    // JpaRepository 已經自帶分頁方法
}