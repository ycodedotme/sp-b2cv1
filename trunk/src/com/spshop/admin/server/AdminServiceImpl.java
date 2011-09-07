package com.spshop.admin.server;


import java.util.List;

import com.spshop.admin.client.businessui.service.AdminService;
import com.spshop.admin.shared.LoginInfo;
import com.spshop.exception.ServiceValidateException;
import com.spshop.model.Category;
import com.spshop.model.Component;
import com.spshop.model.HTML;
import com.spshop.model.Image;
import com.spshop.model.Product;
import com.spshop.model.Site;
import com.spshop.model.TabProduct;
import com.spshop.model.TabSelling;
import com.spshop.model.query.QueryCriteria;
import com.spshop.model.query.QueryResult;
import com.spshop.service.factory.ServiceFactory;
import com.spshop.service.intf.CategoryService;
import com.spshop.service.intf.HTMLService;
import com.spshop.service.intf.ImageService;
import com.spshop.service.intf.ProductService;
import com.spshop.service.intf.SiteService;
import com.spshop.service.intf.TabProductService;
import com.spshop.service.intf.TabSellingService;
import com.spshop.utils.AllConstants;
public class AdminServiceImpl extends RemoteService implements AdminService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5751192707129312608L;

	public QueryResult<Component> query(QueryCriteria criteria)
			throws IllegalArgumentException {
		return ServiceFactory.getService(SiteService.class).query(criteria);
	}

	@Override
	public List<Category> getAllCategory() {
		return ServiceFactory.getService(CategoryService.class).getTopCategories();
	}
	
	@Override
	public List<Category> getAllCategory(boolean includeDisable)
			throws ServiceValidateException {
		return ServiceFactory.getService(CategoryService.class).getTopCategories(includeDisable);
	}

	@Override
	public Category saveCategory(Category category) {
		return ServiceFactory.getService(CategoryService.class).saveCategory(category);
	}

	@Override
	public Image getImageById(long id) {
		Image image=null;
		try {
			image = ServiceFactory.getService(ImageService.class).getImageById(id);
		} catch (Exception e) {
			throw new ServiceValidateException(e.getMessage());
		}
		return image;
	}

	@Override
	public Product saveProduct(Product product)throws ServiceValidateException {
		try {
			product = ServiceFactory.getService(ProductService.class).saveProduct(product);
		} catch (ServiceValidateException e) {
			throw new ServiceValidateException(e.getMessage());
		}
		return product;
	}

	@Override
	public Site saveSite(Site site) throws ServiceValidateException {
		site = ServiceFactory.getService(SiteService.class).save(site).clone();
		LoginInfo loginInfo = getLoginInfo(true);
		loginInfo.setSite(site);
		getThreadLocalRequest().getSession().setAttribute(
				AllConstants.ADMIN_LOGIN_INFO, loginInfo);
		return site;
	}

	@Override
	public TabProduct getTopSelling() throws ServiceValidateException {
		return ServiceFactory.getService(TabProductService.class).getTopSelling();
	}

	@Override
	public TabProduct saveTopSelling(TabProduct product) throws ServiceValidateException {
		return ServiceFactory.getService(TabProductService.class).saveTopSelling(product);
	}

	@Override
	public TabSelling getDefaulTabSelling() throws ServiceValidateException {
		return ServiceFactory.getService(TabSellingService.class).getDefaulTabSelling();
	}

	@Override
	public TabSelling saveTabSelling(TabSelling selling)
			throws ServiceValidateException {
		return ServiceFactory.getService(TabSellingService.class).saveTabSelling(selling);
	}

	@Override
	public TabProduct getTopSelling(long id) throws ServiceValidateException {
		return ServiceFactory.getService(TabProductService.class).getTopSelling(id);
	}

	@Override
	public HTML saveHTML(HTML html) throws ServiceValidateException {
		return ServiceFactory.getService(HTMLService.class).saveHTML(html);
	}

	@Override
	public HTML getHTML(long id) throws ServiceValidateException {
		return ServiceFactory.getService(HTMLService.class).getHTML(id);
	}

}
