package com.real.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.real.model.*;
import com.real.model.購物車;
import com.real.dao.購物車dao;
import com.real.dto.商品dto;
import com.real.dto.購物車dto;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class 購物車controller {
		
	@Autowired
	購物車dao dao;
	
	private List<購物車dto> userList = new ArrayList<>();
	//取得全部購物車資訊
	@RequestMapping(method=RequestMethod.GET)	
	public List<購物車dto> getAll(){
		return dao.getAll();
	}
	//新增購物車資訊
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity add(@RequestBody 購物車dto st) {
		Integer flag=dao.add(st);
		if(flag>=0) {
			return ResponseEntity.ok(flag);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//根據購物車編號取得購物車資訊
	@RequestMapping(value="/{購物車編號}",method=RequestMethod.GET)	
	public ResponseEntity findByshoppingId(@PathVariable("購物車編號")int sid){
		購物車dto s1=dao.findByid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
	
	//根據使用者產生購物車資訊
	@RequestMapping(value="/user/{使用者編號}",method=RequestMethod.GET)	
	public ResponseEntity findByuserId(@PathVariable("使用者編號")int sid){
		List<購物車dto> s1=dao.findByuserid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
	
	//更新購物車資訊
	@RequestMapping(value="/{購物車編號}",method=RequestMethod.PUT)	
	public ResponseEntity updateStudent(@PathVariable("購物車編號")int 購物車編號,@RequestBody 購物車dto obj){
		boolean s1=dao.update(購物車編號, obj);
		if(s1==false)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(obj);	
		
	}
	
	//刪除購物車資訊
	@RequestMapping(value="/{購物車編號}",method=RequestMethod.DELETE)	
   	public ResponseEntity deleteStudent(@PathVariable("購物車編號")int sid){
    	
		購物車dto s1=dao.findByid(sid);
    	boolean flag=dao.delete(sid);
   		
   		if(flag==false)
   			return ResponseEntity.notFound().build();
   		else
   			return ResponseEntity.ok(s1);	
   		
   	}
}
