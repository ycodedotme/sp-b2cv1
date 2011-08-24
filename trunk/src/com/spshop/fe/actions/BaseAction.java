package com.spshop.fe.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.spshop.cache.SCacheManager;
import com.spshop.fe.formbeans.PageFormBean;
import com.spshop.model.Category;
import com.spshop.model.Site;
import com.spshop.service.factory.ServiceFactory;
import com.spshop.service.intf.SiteService;

public abstract class BaseAction extends Action {
	
	/**
	 * Populate MenuBar data for page
	 * 
	 * @param page
	 */
	private void populateMenuBar(PageFormBean page) {
		List<Category> categories = new ArrayList<Category>();
		categories = SCacheManager.getTopCategories();
		
		page.addAllCategories(categories);
	}

	/**
	 * Populate Site Informations for page
	 * 
	 * @param request
	 * @param page
	 */
	private void populateSiteInfo(HttpServletRequest request, PageFormBean page) {
		Site site = ServiceFactory.getService(SiteService.class).getSiteById(1);
		page.setSite(site);
	}
	
	public void populateCategoryForCategoryPage(String categoryName, PageFormBean page) {
		List<Category> categories = SCacheManager.getTopCategories();
		
		page.setCategory(searchCategory(categories, categoryName));
	}
	
	
	/**
	 * Find category from list in cache
	 * 
	 * @param categories
	 *            The target list for finding
	 * @param catName
	 *            The category name
	 * @return Searching result
	 */
	private Category searchCategory(List<Category> categories, String catName) {
		Category result = null;
		
		for (Category category : categories) {
			if (category.getName().equals(catName)) {
				result = category;
				break;
			} else if (category.getSubCategories().size() != 0){
				result = searchCategory(category.getSubCategories(), catName);
				if (result != null)
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageFormBean page = (PageFormBean) form;
		populateMenuBar(page);
		populateSiteInfo(request, page);
		
		return processer(mapping, page, request, response);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ActionForward processer(ActionMapping mapping, PageFormBean page, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
