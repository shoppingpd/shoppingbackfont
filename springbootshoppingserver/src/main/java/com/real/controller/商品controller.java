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
import com.real.model.商品;
import com.real.dao.商品dao;
import com.real.dto.商品dto;

import java.util.*;

@RestController
@RequestMapping("/products")
public class 商品controller {
    
	@Autowired
	商品dao dao;
	
	private List<商品dto> userList = new ArrayList<>();
    @RequestMapping(method=RequestMethod.GET)	
	public List<商品dto> getAllStudents(){
		return dao.getAll();
	}

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity addStudent(@RequestBody 商品dto st) {
    	boolean flag=dao.add(st);
    	if(flag) {
    		return ResponseEntity.ok(st);
    	}else {
    		return ResponseEntity.badRequest().build();
    	}
    }
    
    @RequestMapping(value="/{商品編號}",method=RequestMethod.GET)	
	public ResponseEntity findStudentById(@PathVariable("商品編號")int sid){
    	商品dto s1=dao.findBySid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
    
    @RequestMapping(value="/{商品編號}",method=RequestMethod.PUT)	
	public ResponseEntity updateStudent(@PathVariable("商品編號")int 商品編號,@RequestBody 商品 obj){
		boolean s1=dao.update(商品編號, obj);
		if(s1==false)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(obj);	
		
	}
    
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
