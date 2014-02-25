package com.honeybuy.shop.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.honeybuy.shop.web.cache.TagsServiceCacheWrapper;

@Controller
@RequestMapping("/tags")
public class TagsController {
	
	private static final Logger logger = LoggerFactory.getLogger(TagsController.class);
	
	@Autowired
	private TagsServiceCacheWrapper tagsService;
	
	public static List<String> INDEX_KEYS = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0-9");
	
	public final static int TAG_PER_PAGE = 80; 
	
	@RequestMapping("/index/{indexName}")
	public String tagIndex(@PathVariable("indexName") String indexName,
			Model model){
		return "forward:/tags/index/" + indexName + "/0";
	}
	
	@RequestMapping("/index/{indexName}/{page:\\d+}")
	public String tagIndex(@PathVariable("indexName") String indexName,
			@PathVariable("page") int page,
			Model model){
		if(StringUtils.isEmpty(indexName) || !INDEX_KEYS.contains(indexName)){
			logger.warn("Tag: {} is not existing", indexName);
			return "404";
		}
		int totalCount = tagsService.getTagsCountByIndex(indexName);
		int max = TAG_PER_PAGE;
		
		int totalPage;
		if(totalCount % max == 0) {
			totalPage = totalCount / max;
		} else {
			totalPage = totalCount / max + 1;
		}
		int start = page * max;
		if(start >= totalCount) {
			page = 0;
			start = 0;
		}
		List<String> tags = tagsService.getTagsByIndexWithLimit(indexName, start, max);
		if(tags != null && tags.size() > 0) {
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
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageIds", pageIds);
			model.addAttribute("tags", tags);
		
		}
		model.addAttribute("indexName", indexName);
		model.addAttribute("currentPageIndex", page);
		return "tagIndex";
	}
	
	@RequestMapping("/index/key/{indexName}")
	public String tagDetail(@PathVariable("indexName") String indexName,
			Model model){
		return "getAllTagIndexMap";
	}
	
	@RequestMapping("/index/key/{indexName}/{page:\\d+}")
	public String tagDetail(@PathVariable("indexName") String indexName,
			@PathVariable("page") int page,
			Model model){
		return "getAllTagIndexMap";
	}
	
}
