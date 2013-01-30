/*
 * Project: iSAPort
 * Copyright (c) 2012 HP. All Rights Reserved.
 */
package com.honeybuy.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.honeybuy.shop.dto.SiteDTO;

/**
 * 
 * @author <link href="wan-shan.zhu@hp.com">Spark Zhu</link>
 * @version 1.0
 */
@Service
public class SiteService implements ISiteService{

	@Override
	public SiteDTO getSite() {
		SiteDTO siteDTO = new SiteDTO();
		
		siteDTO.setOwnerId("None");
		siteDTO.setResourceServer("http://localhost:8180/web-resources/hb");
		siteDTO.setSiteId("0");
		
		List<String> css = new ArrayList<String>();
		css.add("/css/trontastic/jquery-ui-1.10.0.custom.css");
		
		List<String> js = new ArrayList<String>();
		js.add("/js/jquery-1.9.0.js");
		js.add("/js/jquery-ui-1.10.0.custom.js");
		
		return siteDTO;
	}

}
