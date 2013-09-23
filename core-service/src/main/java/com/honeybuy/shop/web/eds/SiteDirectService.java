/*
 * Project: iSAPort
 * Copyright (c) 2012 HP. All Rights Reserved.
 */
package com.honeybuy.shop.web.eds;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.hb.core.shared.dto.SiteDTO;

/**
 * 
 * @author <link href="wan-shan.zhu@hp.com">Spark Zhu</link>
 * @version 1.0
 */
@Service
public class SiteDirectService{

	@Cacheable(cacheName="siteDTO")
	public SiteDTO getSite() {
		SiteDTO siteDTO = new SiteDTO();
		
		siteDTO.setResourceServer("http://localhost");
		siteDTO.setWebResourcesFolder("/rs");
		siteDTO.setProductImageResourcesFolder("/img");
		
		List<String> css = new ArrayList<String>();
		List<String> js = new ArrayList<String>();
		
		siteDTO.setCss(css);
		siteDTO.setJs(js);
		
		siteDTO.setSiteName("HoneyBuy");
		siteDTO.setDomain("http://localhost:88");
		
		
		return siteDTO;
	}

}
