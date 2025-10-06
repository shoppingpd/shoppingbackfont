package com.real.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.real.dao.使用者dao;
import com.real.dao.商品dao;
import com.real.dto.使用者dto;
import com.real.dto.商品dto;
import com.real.model.*;


@RestController
@RequestMapping("/user")
public class 使用者controller {
	@Autowired
	使用者dao dao;
	
	private List<使用者dto> userList = new ArrayList<>();
	
	//取得全部商品資訊
    @RequestMapping(method=RequestMethod.GET)	
	public List<使用者dto> getAll(){
		return dao.getAll();
	}

	//新增商品資訊
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity addStudent(@RequestBody 使用者dto st) {
    	boolean flag=dao.add(st);
    	if(flag) {
    		return ResponseEntity.ok(st);
    	}else {
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    //根據使用者編號取得使用者資訊
    @RequestMapping(value="/{使用者編號}",method=RequestMethod.GET)	
	public ResponseEntity findStudentById(@PathVariable("使用者編號")int sid){
    	使用者dto s1=dao.findBySid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
    
    //根據使用者編號更改使用者資訊
    @RequestMapping(value="/{使用者編號}",method=RequestMethod.PUT)	
	public ResponseEntity updateStudent(@PathVariable("使用者編號")int 使用者編號,@RequestBody 使用者dto obj){
		boolean s1=dao.update(使用者編號, obj);
		if(s1==false)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(obj);	
		
	}
    
    //根據使用者編號刪除使用者資訊
    @RequestMapping(value="/{使用者編號}",method=RequestMethod.DELETE)	
   	public ResponseEntity deleteStudent(@PathVariable("使用者編號")int sid){
    	
    	使用者dto s1=dao.findBySid(sid);
    	boolean flag=dao.delete(sid);
   		
   		if(flag==false)
   			return ResponseEntity.notFound().build();
   		else
   			return ResponseEntity.ok(s1);	
   		
   	}
}
