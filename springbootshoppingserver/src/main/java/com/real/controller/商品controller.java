package com.real.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.real.model.*;
import com.real.model.商品;
import com.real.myinterface.商品Repository;
import com.real.dao.商品dao;
import com.real.dto.商品dto;

import java.util.*;

@RestController
@RequestMapping("/products")
public class 商品controller {
    
	@Autowired
	商品dao dao;
	
	private List<商品dto> userList = new ArrayList<>();
	
	//取得全部商品資訊
    @RequestMapping(method=RequestMethod.GET)	
	public List<商品dto> getAll(){
		return dao.getAll();
	}
	
    // 分頁 API
    @GetMapping("/page/{頁碼}")
    public List<商品dto> getProducts(
            @PathVariable("頁碼") Integer pageNo,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(defaultValue = "商品編號") String sortBy) {
    		int pageIndex = pageNo - 1; // 前端 1 -> PageRequest 0
        if(pageIndex < 0) pageIndex = 0; // 避免負數
        return dao.getPage(pageNo, size, sortBy);
    }
    // 取得最大頁數 API
    @GetMapping("/maxPage")
    public int getMaxPage() {
        return dao.findMaxPage();  // 直接用 Bean 方法就行
    }


	//新增商品資訊
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity addStudent(@RequestBody 商品dto st) {
    	boolean flag=dao.add(st);
    	if(flag) {
    		return ResponseEntity.ok(st);
    	}else {
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    //根據商品編號取得商品資訊
    @RequestMapping(value="/{商品編號}",method=RequestMethod.GET)	
	public ResponseEntity findStudentById(@PathVariable("商品編號")int sid){
    	商品dto s1=dao.findBySid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
    
    //根據商品編號取得商品資訊
    @RequestMapping(value="/user/{使用者編號編號}",method=RequestMethod.GET)	
	public ResponseEntity findStudentByuserId(@PathVariable("使用者編號編號")int sid){
    	List<商品dto> s1=dao.findStudentByuserId(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
    
    //根據商品編號更改商品資訊
    @RequestMapping(value="/{商品編號}",method=RequestMethod.PUT)	
	public ResponseEntity updateStudent(@PathVariable("商品編號")int 商品編號,@RequestBody 商品dto obj){
		boolean s1=dao.update(商品編號, obj);
		if(s1==false)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(obj);	
		
	}
    
    //根據商品編號刪除商品資訊
    @RequestMapping(value="/{商品編號}",method=RequestMethod.DELETE)	
   	public ResponseEntity deleteStudent(@PathVariable("商品編號")int sid){
    	
    	商品dto s1=dao.findBySid(sid);
    	boolean flag=dao.delete(sid);
   		
   		if(flag==false)
   			return ResponseEntity.notFound().build();
   		else
   			return ResponseEntity.ok(s1);	
   		
   	}
    
}
