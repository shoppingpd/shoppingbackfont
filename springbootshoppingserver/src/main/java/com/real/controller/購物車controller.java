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
import com.real.dto.購物車dto;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class 購物車controller {
		
	@Autowired
	購物車dao dao;
	
	private List<購物車dto> userList = new ArrayList<>();
	@RequestMapping(method=RequestMethod.GET)	
	public List<購物車dto> getAllStudents(){
		return dao.getAll();
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity addStudent(@RequestBody 購物車dto st) {
		boolean flag=dao.add(st);
		if(flag) {
			return ResponseEntity.ok(st);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value="/{購物車編號}",method=RequestMethod.GET)	
	public ResponseEntity findStudentById(@PathVariable("購物車編號")int sid){
		購物車dto s1=dao.findBySid(sid);
		if(s1==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(s1);	
		
	}
	
	@RequestMapping(value="/{購物車編號}",method=RequestMethod.PUT)	
	public ResponseEntity updateStudent(@PathVariable("購物車編號")int 購物車編號,@RequestBody 購物車dto obj){
		boolean s1=dao.update(購物車編號, obj);
		if(s1==false)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(obj);	
		
	}
}
