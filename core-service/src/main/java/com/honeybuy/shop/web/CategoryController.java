/*
 * Project: iSAPort
 * Copyright (c) 2012 HP. All Rights Reserved.
 */
package com.honeybuy.shop.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hb.core.shared.dto.CategoryDetailDTO;
import com.hb.core.shared.dto.ProductSummaryDTO;
import com.honeybuy.shop.web.cache.CategoryServiceCacheWrapper;
import com.honeybuy.shop.web.cache.ProductServiceCacheWrapper;

/**
 * 
 * @author <link href="wan-shan.zhu@hp.com">Spark Zhu</link>
 * @version 1.0
 */
@Controller
@RequestMapping("")
public class CategoryController {
	
	@Autowired
	private ProductServiceCacheWrapper productService;
	
	@Autowired
	private CategoryServiceCacheWrapper categoryService;
	
	@RequestMapping("/c/{categoryName}/{page:\\d+}")
	public String productDetail(@PathVariable("categoryName") String categoryName,@PathVariable("page") int page,  Model model){
		CategoryDetailDTO categoryDetailDTO =  categoryService.getCategoryDetailByName(categoryName);
		if(null == categoryDetailDTO){
			return "404";
		}
		
		long categoryId = categoryDetailDTO.getId();
		int totalCount = productService.getProductCountByCategoryId(categoryId);
		int max = 24;
		
		int totalPage = totalCount / max + 1;
		int start = page * max;
		if(start >= totalCount) {
			page = 0;
			start = 0;
		}
		List<ProductSummaryDTO> productSummaryList = productService.getAllProductByCategoryId(categoryId, start, max);
		
		if(productSummaryList.size() > 0) {
			List<Integer> pageIds = new ArrayList<Integer>();
			if(totalPage <= 7) {
				for(int i = 0; i < totalPage; i++) {
					pageIds.add(i);
				}
			} else {
				if(page < 3) {
					for(int i = 0; i < 5; i++) {
						pageIds.add(i);
					}
					pageIds.add(-1);
					pageIds.add(totalPage - 1);
				} else if(page >= (totalPage - 3)) {
					pageIds.add(0);
					pageIds.add(-1);
					for(int i = totalPage - 5; i < totalPage; i++) {
						pageIds.add(i);
					}
				} else {
					pageIds.add(0);
					pageIds.add(-1);
					for(int i = -1; i <= 1; i++) {
						pageIds.add(page + i);
					}
					pageIds.add(-1);
					pageIds.add(totalPage - 1);
				}
			}
			model.addAttribute("resultStart", start + 1);
			model.addAttribute("resultEnd", start + productSummaryList.size());
			model.addAttribute("resultTotal", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageIds", pageIds);
			model.addAttribute("currentCategoryDetail", categoryDetailDTO);
			model.addAttribute("productSummary", productSummaryList);
			model.addAttribute("currentCategoryPageIndex", page);
			model.addAttribute("noproduct", false);
		} else {
			model.addAttribute("noproduct", true);
		}
		return "categoryIndex";
	}
	
	@RequestMapping("/c/{categoryName}")
	public String productDetail(@PathVariable("categoryName") String categoryName, Model model){
		return "forward:/c/"+categoryName+"/0";
	}
	
}