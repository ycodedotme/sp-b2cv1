/*
 * Project: iSAPort
 * Copyright (c) 2012 HP. All Rights Reserved.
 */
package com.honeybuy.shop.web;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hb.core.shared.dto.CategoryTreeDTO;
import com.honeybuy.shop.web.cache.CategoryServiceCacheWrapper;
import com.honeybuy.shop.web.cache.SitemapServiceCacheWrapper;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author <link href="wan-shan.zhu@hp.com">Spark Zhu</link>
 * @version 1.0
 */
@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	private CategoryServiceCacheWrapper categoryService;
	
	@Autowired
	private SitemapServiceCacheWrapper sitemapService;
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	
	@RequestMapping("/")
	public String defaultPage(){
		return "redirect:/home";
	}
	
	
	@RequestMapping("/loging")
	public String loging(){
		return "loging";
	}
	
	@RequestMapping("/404")
	public String notFount(){
		return "404";
	}
	
	@RequestMapping("/500")
	public String serverError(){
		return "500";
	}
	
	@RequestMapping("/iWantAnError")
	public String iWantAnError(){
		throw new RuntimeException();
	}
	
	@RequestMapping("/testJson")
	@ResponseBody
	public String test(){
		return "asfasfjkldgjdklfgjdfklgjdfklgjdfklg";
	}
	
	@RequestMapping("/sitemap.xml")
	@ResponseBody
	@Produces("application/xml")
	public String sitemap(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/xml");
		return "<xml>ljsl</xml>";
	}
	
	@RequestMapping("/category.xml")
	@ResponseBody
	@Produces("application/xml")
	public String category(HttpServletRequest request, HttpServletResponse response){
		return sitemapService.getCategoryXml();
	}
	
	@RequestMapping("/p1.xml")
	@ResponseBody
	@Produces("application/xml")
	public String product(HttpServletRequest request, HttpServletResponse response){
		return sitemapService.getProductXml();
	}
}
