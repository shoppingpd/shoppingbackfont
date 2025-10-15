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
import com.real.model.訂單;
import com.real.dao.訂單dao;
import com.real.dto.商品dto;
import com.real.dto.訂單dto;
import java.util.*;

@RestController
@RequestMapping("/list")
public class 訂單controller {
		
	@Autowired
	訂單dao dao;
	
	private List<訂單dto> userList = new ArrayList<>();
	//取得全部訂單資訊
	@RequestMapping(method=RequestMethod.GET)	
	public List<訂單dto> getAll(){
		return dao.getAll();
	}
	//新增訂單資訊
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity add(@RequestBody 訂單dto st) {
		Integer flag=dao.add(st);
		if(flag>=0) {
			return ResponseEntity.ok(flag);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//根據訂單編號取得訂單資訊
	@RequestMapping(value="/{訂單編號}",method=RequestMethod.GET)	
	public ResponseEntity findByshoppingId(@PathVariable("訂單編號")int sid){
		訂單dto s1=dao.findByid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
	
	//根據使用者產生訂單資訊
	@RequestMapping(value="/user/{使用者編號}",method=RequestMethod.GET)	
	public ResponseEntity findByuserId(@PathVariable("使用者編號")int sid){
		List<訂單dto> s1=dao.findByuserid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
	
	//更新訂單資訊
	@RequestMapping(value="/{訂單編號}",method=RequestMethod.PUT)	
	public ResponseEntity updateStudent(@PathVariable("訂單編號")int 訂單編號,@RequestBody 訂單dto obj){
		boolean s1=dao.update(訂單編號, obj);
		if(s1==false)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(obj);	
		
	}
	
	//刪除訂單資訊
	@RequestMapping(value="/{訂單編號}",method=RequestMethod.DELETE)	
   	public ResponseEntity deleteStudent(@PathVariable("訂單編號")int sid){
    	
		訂單dto s1=dao.findByid(sid);
    	boolean flag=dao.delete(sid);
   		
   		if(flag==false)
   			return ResponseEntity.notFound().build();
   		else
   			return ResponseEntity.ok(s1);	
   		
   	}
}
