package com.spshop.admin.client.businessui.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spshop.admin.shared.LoginInfo;
import com.spshop.exception.ServiceValidateException;
import com.spshop.model.Category;
import com.spshop.model.Component;
import com.spshop.model.Image;
import com.spshop.model.Product;
import com.spshop.model.query.QueryCriteria;
import com.spshop.model.query.QueryResult;
@RemoteServiceRelativePath("admin")
public interface AdminService  extends RemoteService{
	QueryResult<Component> query(QueryCriteria criteria) throws IllegalArgumentException;
	LoginInfo getLoginInfo()throws IllegalArgumentException;
	List<Category> getAllCategory()throws ServiceValidateException;
	List<Category> getAllCategory(boolean includeDisable)throws ServiceValidateException;
	Category saveCategory(Category category)throws ServiceValidateException;
	Image getImageById(long id)throws ServiceValidateException;
	Product saveProduct(Product product)throws ServiceValidateException;
}

