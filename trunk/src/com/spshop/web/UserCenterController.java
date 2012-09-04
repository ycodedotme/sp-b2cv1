package com.spshop.web;

import static com.spshop.utils.Constants.ADDRESS1;
import static com.spshop.utils.Constants.ADDRESS1_ERR;
import static com.spshop.utils.Constants.ADDRESS2;
import static com.spshop.utils.Constants.ADDRESS2_ERR;
import static com.spshop.utils.Constants.ADD_TYPE;
import static com.spshop.utils.Constants.ADD_TYPE_B;
import static com.spshop.utils.Constants.ADD_TYPE_P;
import static com.spshop.utils.Constants.BILLING_ADDRESS;
import static com.spshop.utils.Constants.BILLING_SAME_AS_PRIMARY;
import static com.spshop.utils.Constants.CITY;
import static com.spshop.utils.Constants.CITY_ERR;
import static com.spshop.utils.Constants.COUNTRY;
import static com.spshop.utils.Constants.COUNTRY_ERR;
import static com.spshop.utils.Constants.CURRENT_ORDER;
import static com.spshop.utils.Constants.CURRENT_PRODUCT;
import static com.spshop.utils.Constants.CURRENT_PRODUCT_ID;
import static com.spshop.utils.Constants.C_USER_FIRST_NAME;
import static com.spshop.utils.Constants.C_USER_LAST_NAME;
import static com.spshop.utils.Constants.DEFAULT_CURRENCY;
import static com.spshop.utils.Constants.EMPTY_ORDER;
import static com.spshop.utils.Constants.FIRST_NAME_ERR;
import static com.spshop.utils.Constants.LAST_NAME_ERR;
import static com.spshop.utils.Constants.MEASUREMENT_MSG;
import static com.spshop.utils.Constants.PAGINATION;
import static com.spshop.utils.Constants.POASTAL_CODE;
import static com.spshop.utils.Constants.POSTAL_CODE_ERR;
import static com.spshop.utils.Constants.PRIMARY_ADDRESS;
import static com.spshop.utils.Constants.REG_PWD_RE_ERR;
import static com.spshop.utils.Constants.REG_USER_NAME_SUC;
import static com.spshop.utils.Constants.SHIPPING_EXPEDITED;
import static com.spshop.utils.Constants.SHIPPING_METHOD;
import static com.spshop.utils.Constants.SHIPPING_STANDARD;
import static com.spshop.utils.Constants.SITE_VIEW;
import static com.spshop.utils.Constants.STATE_PROVINCE;
import static com.spshop.utils.Constants.STATE_PROVINCE_ERR;
import static com.spshop.utils.Constants.SUIT_MEASUREMENT;
import static com.spshop.utils.Constants.TEL_NUM;
import static com.spshop.utils.Constants.TEL_NUM_ERR;
import static com.spshop.utils.Constants.TXT_NEW_PWD1;
import static com.spshop.utils.Constants.TXT_NEW_PWD2;
import static com.spshop.utils.Constants.TXT_PWD;
import static com.spshop.utils.Constants.UPDATE_ACC_SUC;
import static com.spshop.utils.Constants.UPDATE_ADDRESS_1_SUC;
import static com.spshop.utils.Constants.UPDATE_ADDRESS_2_SUC;
import static com.spshop.utils.Constants.USERNAME;
import static com.spshop.utils.Constants.USERNAME_ERR;
import static com.spshop.utils.Constants.USER_ORDERS;
import static com.spshop.utils.Constants.USER_ORDERS_COUNT;
import static com.spshop.utils.Constants.WRONG_PWD;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spshop.model.Address;
import com.spshop.model.Country;
import com.spshop.model.Message;
import com.spshop.model.Order;
import com.spshop.model.OrderItem;
import com.spshop.model.SuitMeasurement;
import com.spshop.model.User;
import com.spshop.model.enums.OrderStatus;
import com.spshop.service.factory.ServiceFactory;
import com.spshop.service.intf.CountryService;
import com.spshop.service.intf.MessageService;
import com.spshop.service.intf.OrderService;
import com.spshop.service.intf.UserService;
import com.spshop.utils.EmailTools;
import com.spshop.utils.Utils;
import com.spshop.web.view.SiteView;

@Controller
@SessionAttributes("currentProductID")
public class UserCenterController extends BaseController{
	
	Logger logger = Logger.getLogger(UserCenterController.class);
	
	@RequestMapping("/changePwd")
	public String changePwd(Model model) {
		return "changePwd";
	}
	
	@RequestMapping(value = "/changePwd", method=RequestMethod.POST)
	public String changePwd2(Model model, HttpServletRequest request) {
		String oldPWD = request.getParameter(TXT_PWD);
		String pwd1 = request.getParameter(TXT_NEW_PWD1);
		String pwd2 = request.getParameter(TXT_NEW_PWD2);
		User user = ServiceFactory.getService(UserService.class).queryUserByEmail(getUserView().getLoginUser().getEmail());
		if(null!=user && !user.getPassword().equals(oldPWD)){
			getUserView().getErr().put(WRONG_PWD, "Original password is wrong");
		}
		
		if(pwd1 == null || pwd1.length()<6){
			getUserView().getErr().put(REG_PWD_RE_ERR, "Invalid password");
		}else if(!pwd1.equals(pwd2)){
			getUserView().getErr().put(REG_PWD_RE_ERR, "Two new passwords are not same");
		}
		
		if(getUserView().getErr().isEmpty() && null!=user){
			user.setPassword(pwd1);
			user = ServiceFactory.getService(UserService.class).save(user);
			getUserView().getMsg().put(REG_USER_NAME_SUC, "Update successfully");
		}
		
		return "changePwd";
	}
	
	@RequestMapping("/userProfile")
	public String userProfile(Model model) {
		return "userProfile";
	}
	
	@RequestMapping("/retrieveShippingPrice")
	public String retrieveShippingPrice(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam("cc") int cc) throws IOException {
		
		Country country = ServiceFactory.getService(CountryService.class).getCountryById(cc);
		
		Map<String, String> rs = new HashMap<String, String>();
		
		String shippingMethod = request.getParameter(SHIPPING_METHOD);
		
		if(null!=country){
		
			float dePrice = 0f;
			float adDePrice = 0f;
			
			if(getUserView().getCart().getOrder().getTotalPrice() > country.getFreeAdDePrice()){
				adDePrice = 0;
			}else{
				adDePrice = country.getAdDePrice();
			}
			
			if(getUserView().getCart().getOrder().getTotalPrice() > country.getFreeDePrice()){
				dePrice = 0;
			}else{
				dePrice = country.getDePrice();
			}
			
			
			if(SHIPPING_EXPEDITED.equals(shippingMethod)){
				
				rs.put("grandTotal", Utils.toNumber((getUserView().getCart().getOrder().getTotalPrice()+adDePrice-getUserView().getCart().getOrder().getCouponCutOff())*getUserView().getCurrencyRate()));
				rs.put("shippingCost", Utils.toNumber(adDePrice*getUserView().getCurrencyRate()));
			}else{
				
				
				rs.put("grandTotal", Utils.toNumber((getUserView().getCart().getOrder().getTotalPrice()+dePrice-getUserView().getCart().getOrder().getCouponCutOff())*getUserView().getCurrencyRate()));
				rs.put("shippingCost", Utils.toNumber(dePrice*getUserView().getCurrencyRate()));
			}
			
			if(null!=country){
				rs.put(SHIPPING_STANDARD, Utils.toNumber(dePrice*getUserView().getCurrencyRate()));
				rs.put(SHIPPING_EXPEDITED, Utils.toNumber(adDePrice*getUserView().getCurrencyRate()));
				
			}
		}
		
		JSONObject jsonObject = JSONObject.fromObject(rs);
		
		response.getWriter().print(jsonObject);
		
		return null;
	}
	
	@RequestMapping(value="/shoppingCart_address",method=RequestMethod.GET)
	public String shoppingCartAdress(Model model) {
		if(getUserView().getCart().getItemCount() < 1){
			getUserView().getErr().put(EMPTY_ORDER, "Shopping cart is empty");
			return "shoppingCart";
		}
		
		for(OrderItem orderItem : getUserView().getCart().getOrder().getItems()){
			if(orderItem.getProduct().getOptType() == 1){
				SuitMeasurement measurement = getUserView().getLoginUser().getSuitMeasurement();
				if(null != validate(measurement)){
					model.addAttribute(CURRENT_PRODUCT, orderItem.getProduct());
					getUserView().getMsg().put(MEASUREMENT_MSG, "You need fill the suit measurement then continue...");
					return "/my-measurements";
				}else{
					getUserView().getCart().getOrder().setMySuitMeasurement(measurement);
					getUserView().getCart().getOrder().setSuitMeasurementComplete(true);
					break;
				}
			}
		}
		
		
		Address primary = null;
		Address billing = null;
		
		
		getUserView().getCart().getOrder().setCustomerEmail(getUserView().getLoginUser().getEmail());
		
		if(StringUtils.isNotBlank(getUserView().getCart().getOrder().getCustomerName())&&StringUtils.isNotBlank(getUserView().getCart().getOrder().getPrimaryAddress().getPhone())){
			primary = getUserView().getCart().getOrder().getPrimaryAddress();
		}else{
			primary = getUserView().getLoginUser().getPrimaryAddress();
		}
		

		if(StringUtils.isNotBlank(getUserView().getCart().getOrder().getBcustomerName())){
			billing = getUserView().getCart().getOrder().getBillingAddress();
		}else{
			billing = getUserView().getLoginUser().getBillingAddress();
		}
		
		model.addAttribute(ADD_TYPE_P, primary);
		model.addAttribute(ADD_TYPE_B, billing);
		
		
		return "shoppingCart_address";
	}
	
	@RequestMapping("/shoppingCart_payment")
	public String shoppingCartPayment(Model model) {
		return "shoppingCart_payment";
	}
	

	@RequestMapping("/shoppingCart_payment2")
	public String shoppingCartPayment(Model model, @RequestParam("id") String orderId) {
		
		Order order = ServiceFactory.getService(OrderService.class).getOrderById(orderId);
		if(null!=order && OrderStatus.PENDING.getValue().equals(order.getStatus())){
			model.addAttribute(CURRENT_ORDER, order);
		}else{
			model.addAttribute(CURRENT_ORDER, order);
		}
		
		return "paypal";
	}
	
	@RequestMapping(value = "/shoppingCart_payment_2_pay" , method = RequestMethod.POST)
	public String shoppingCartPayment2Pay(Model model) {
		
		Order order = getUserView().getCart().getOrder();
		
		getUserView().getCart().setOrder(new Order());
		
		order = ServiceFactory.getService(OrderService.class).saveOrder(order, OrderStatus.PENDING.toString());
		
		model.addAttribute(CURRENT_ORDER,order);
		
		if(null!=order && !order.getItems().isEmpty()){
			
			final Order o = order;
			
			final Map<String,Object> root = new HashMap<String,Object>(); 
			
			final SiteView siteView =  getSiteView();
			
			root.put("order", order);
			
			float currencyRate = 1;
			
			if(!DEFAULT_CURRENCY.equals(o.getCurrency())){
				currencyRate =  getSiteView().getCurrencies().get(o.getCurrency());
			}
			
			
			
			root.put("currencyRate",currencyRate);
			root.put(SITE_VIEW,siteView);
			new Thread(){
				public void run() {
					try{
						EmailTools.sendMail("paid", "Order Received, Awaiting Payment Confirmation", root,o.getUser().getEmail());
					}catch(Exception e){
						logger.debug(e);
					}
				};
			}.start();
			return "paypal";
		}
		
		return "shoppingCart_payment";
	}
	
	@RequestMapping(value="/shoppingCart_address",method=RequestMethod.POST)
	public String submitAddressInfo(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Address primary = retrieveAddress(request,ADD_TYPE_P);
		Address billing = retrieveAddress(request,ADD_TYPE_B);
		String billingAsPrimary = request.getParameter(BILLING_SAME_AS_PRIMARY);
		String shippingMethod = request.getParameter(SHIPPING_METHOD);
		
		getUserView().getCart().getOrder().setPrimaryAddress(primary);
		getUserView().getCart().getOrder().setBillingAddress(billing);
		
		Map<String,String> vs = validateAddress(getUserView().getCart().getOrder().getPrimaryAddress(), ADD_TYPE_P);
		
		
		if(null == billingAsPrimary){
			vs.putAll(validateAddress(getUserView().getCart().getOrder().getBillingAddress(), ADD_TYPE_B));
			getUserView().getCart().getOrder().setBillingSameAsPrimary(false);
		}else{
			getUserView().getCart().getOrder().setBillingSameAsPrimary(true);
		}
		
		if(SHIPPING_STANDARD.equals(shippingMethod)){
			getUserView().getCart().getOrder().setShippingMethod(SHIPPING_STANDARD);
		}else{
			getUserView().getCart().getOrder().setShippingMethod(SHIPPING_EXPEDITED);
		}
		
		getUserView().setErr(vs);
		
		if(MapUtils.isNotEmpty(getUserView().getErr())){
			return "shoppingCart_address";
		}
		
		
		Country country = ServiceFactory.getService(CountryService.class).getCountryById(getUserView().getCart().getOrder().getPrimaryAddress().getCountry());
		
		getUserView().getCart().getOrder().setCustomerCountry(country.getName());
		
		if(!getUserView().getCart().getOrder().isBillingSameAsPrimary()){
			Country country1 = ServiceFactory.getService(CountryService.class).getCountryById(getUserView().getCart().getOrder().getBillingAddress().getCountry());
			getUserView().getCart().getOrder().setCustomerBCountry(country1.getName());
		}
		
		if(SHIPPING_STANDARD.equals(getUserView().getCart().getOrder().getShippingMethod())){
			if(getUserView().getCart().getOrder().getTotalPrice() < country.getFreeDePrice() ){
				getUserView().getCart().getOrder().setDePrice(country.getDePrice());
			}else{
				getUserView().getCart().getOrder().setDePrice(0f);
			}
		}else{
			if(getUserView().getCart().getOrder().getTotalPrice() < country.getFreeAdDePrice() ){
				getUserView().getCart().getOrder().setDePrice(country.getAdDePrice());
			}else{
				getUserView().getCart().getOrder().setDePrice(0f);
			}
		}
		
		ServiceFactory.getService(OrderService.class).saveOrder(getUserView().getCart().getOrder(), OrderStatus.ONSHOPPING.toString());
		
		return "redirect:/uc/shoppingCart_payment";
	}
	
	@RequestMapping(value="/userProfile", params={"action=updateAccount"},method=RequestMethod.POST)
    public String updateAccount(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String firstName = request.getParameter(C_USER_FIRST_NAME);
		String lastName = request.getParameter(C_USER_LAST_NAME);
		
		if(StringUtils.isBlank(firstName)||firstName.length()>50){
			getUserView().getErr().put(FIRST_NAME_ERR, "invalid first name");
		}
		
		if(StringUtils.isBlank(lastName)||lastName.length()>50){
			getUserView().getErr().put(LAST_NAME_ERR, "invalid last name");
		}
		
		if(getUserView().getErr().isEmpty()){
			User user = getUserView().getLoginUser();
			
			user.setFirstName(firstName);
			user.setLastName(lastName);
			
			ServiceFactory.getService(UserService.class).saveUser(user);
			
			getUserView().getMsg().put(UPDATE_ACC_SUC, "Update successfully");
			
		}
		
        return "userProfile";
    }
	
	@RequestMapping(value="/userProfile", params={"action=updateAddress1"},method=RequestMethod.POST)
    public String updateAddress1(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		String type = request.getParameter(ADD_TYPE);
		
		Address address = retrieveAddress(request);
		
		getUserView().getErr().putAll(validateAddress(address, type));
		
		if(getUserView().getErr().isEmpty()){
			User user = getUserView().getLoginUser();
			user.setPrimaryAddress(address);
			user = ServiceFactory.getService(UserService.class).saveUser(user);
			getUserView().getMsg().put(UPDATE_ADDRESS_1_SUC, "Update successfully");
		}
		
		model.addAttribute(PRIMARY_ADDRESS, address);
		
        return "userProfile";
    }
	
	@RequestMapping(value="/userProfile", params={"action=updateAddress2"},method=RequestMethod.POST)
    public String updateAddress2(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		
		String type = request.getParameter(ADD_TYPE);
		String sameAsPrimary = request.getParameter(BILLING_SAME_AS_PRIMARY);
		Address address = retrieveAddress(request);
		if(!StringUtils.isNotBlank(sameAsPrimary)){
			getUserView().getErr().putAll(validateAddress(address, type));
			
			if(getUserView().getErr().isEmpty()){
				User user = getUserView().getLoginUser();
				user.setBillingAddress(address);
				user.setBillingSameAsPrimary(false);
				user = ServiceFactory.getService(UserService.class).saveUser(user);
				getUserView().setLoginUser(user);
				getUserView().getMsg().put(UPDATE_ADDRESS_2_SUC, "Update successfully");
			}
		}else{
			User user = getUserView().getLoginUser();
			user.setBillingSameAsPrimary(true);
			user = ServiceFactory.getService(UserService.class).saveUser(user);
			getUserView().setLoginUser(user);
			getUserView().getMsg().put(UPDATE_ADDRESS_2_SUC, "Update successfully");
		}
		
		model.addAttribute(BILLING_ADDRESS, address);
		
        return "userProfile";
    }
	
	@RequestMapping("/myOrder")
	public String myOrder(Model model, HttpServletRequest request) {
		
		String strPage = request.getParameter(PAGINATION);
		
		int start = 1;
		
		try{
			start = Integer.parseInt(strPage);
		}catch(Exception e){
		}
		
		if(start < 1){
			start = 1;
		}
		
		List<Order> orders = ServiceFactory.getService(OrderService.class).getOrdersByUserId(getUserView().getLoginUser().getId(), start);
		
		int count = ServiceFactory.getService(OrderService.class).countOrdersByUserId(getUserView().getLoginUser().getId());
		
		model.addAttribute(USER_ORDERS, orders);
		model.addAttribute(USER_ORDERS_COUNT, count);
		model.addAttribute(PAGINATION, start);
		
		
		return "userOrder";
	}
	
	@RequestMapping("/orderDetails")
	public String orderDetails(Model model, HttpServletRequest request,@RequestParam("id") String orderId) {
		
		Order order = ServiceFactory.getService(OrderService.class).getOrderById(orderId);
		
		model.addAttribute(CURRENT_ORDER, order);
		
		return "orderDetails";
	}
	
	@RequestMapping(value="/my-measurements", method = RequestMethod.GET)
	public String measurements(Model model,HttpServletRequest request,HttpServletResponse response){
		
		String cpid = (String) request.getSession().getAttribute(CURRENT_PRODUCT_ID);
		
		if(StringUtils.isNotBlank(cpid)){
			model.addAttribute(CURRENT_PRODUCT_ID, cpid);
		}
		
		if(StringUtils.isNotBlank(request.getParameter("editMode"))){
			model.addAttribute("editMode", true);
		}
		
		SuitMeasurement measurement = getUserView().getLoginUser().getSuitMeasurement();
		
		model.addAttribute(SUIT_MEASUREMENT, measurement);
		
		if(getUserView().getLoginUser().isSuitMeasurementComplete()){
			model.addAttribute(MEASUREMENT_MSG,"You have filled the suit measurement");
		}else{
			model.addAttribute(MEASUREMENT_MSG,"You have not filled the suit measurement");
		}
		
		return "measurements";
	}
	
	@RequestMapping(value="/my-measurements", method = RequestMethod.POST)
	public String measurementsPost(Model model,HttpServletRequest request,HttpServletResponse response){
		
		SuitMeasurement measurement = retrieveSuitMeasurement(request);
		
		model.addAttribute(SUIT_MEASUREMENT, measurement);
		
		String validationString = validate(measurement);
		if(null != validate(measurement)){
			getUserView().getMsg().put(MEASUREMENT_MSG, validationString);
			return "/my-measurements";
		}else{
			getUserView().getLoginUser().setMySuitMeasurement(measurement);
			getUserView().getLoginUser().setSuitMeasurementComplete(true);
		}
		
		ServiceFactory.getService(UserService.class).saveUser(getUserView().getLoginUser());
		
		model.addAttribute(MEASUREMENT_MSG,"Update measurement successfully");
		
		return "measurements";
	}
	
	
	private Address retrieveAddress(HttpServletRequest request){
		String type = request.getParameter(ADD_TYPE);
		return retrieveAddress(request,type);
	}
	
	private Address retrieveAddress(HttpServletRequest request,String type){
		String userName = request.getParameter(type+USERNAME);
		String add1 = request.getParameter(type+ADDRESS1);
		String add2 = request.getParameter(type+ADDRESS2);
		String city = request.getParameter(type+CITY);
		String stateP = request.getParameter(type+STATE_PROVINCE);
		String c = request.getParameter(type+COUNTRY);
		String postalCode = request.getParameter(type+POASTAL_CODE);
		String telNum = request.getParameter(type+TEL_NUM);
		
		int intC = 0;
		
		try {
			intC =  Integer.parseInt(c);
		} catch (NumberFormatException e) {
		}
		
		return new Address(userName, add1, add2, city, stateP, intC, postalCode, telNum);
	}
	
	private Map<String,String> validateAddress(Address address,String type){
		Map<String, String> err = new HashMap<String,String>();
		
		if(StringUtils.isBlank(address.getFullName())||address.getFullName().length()>100){
			err.put(type+USERNAME_ERR, "Invalid username");
		}
		
		if(StringUtils.isBlank(address.getAddress1())||address.getAddress1().length()>200){
			err.put(type+ADDRESS1_ERR, "Invalid address");
		}
		
		if(StringUtils.isNotBlank(address.getAddress2())&&address.getAddress2().length()>200){
			err.put(type+ADDRESS2_ERR, "Invalid address");
		}
		
		if(StringUtils.isBlank(address.getCity())||address.getCity().length()>100){
			err.put(type+CITY_ERR, "Invalid city");
		}
		
		if(null == ServiceFactory.getService(CountryService.class).getCountryById(address.getCountry())){
			err.put(type+COUNTRY_ERR, "Invalid country");
		}
		
		if(StringUtils.isBlank(address.getStateProvince())||address.getStateProvince().length()>100){
			err.put(type+STATE_PROVINCE_ERR, "Invalid state");
		}
		
		if(StringUtils.isBlank(address.getPostalCode())||address.getPostalCode().length()>100){
			err.put(type+POSTAL_CODE_ERR, "Invalid postal code");
		}
		
		if(StringUtils.isBlank(address.getPhone())||address.getPhone().length()>100){
			err.put(type+TEL_NUM_ERR, "Invalid phone number");
		}
		
		return err;
	}
	
    @RequestMapping("/feedback")
    public String postMessage(Model model, HttpServletRequest request, HttpServletResponse response) {
        String optType = request.getParameter("optType");
        if ("compose".equals(optType)) {
            return "composeMsg";
        } else if ("viewThread".equals(optType)) {
            retrieveMessageThread(model, request);
            model.addAttribute("currentUserId", getUserView().getLoginUser().getId());
            return "feedback";
        } else if ("postMsg".equals(optType)) {
            Message message = populateMessage(request);
            String lastMsgId = request.getParameter("lastMsgId"); 
            if (lastMsgId != null) {
                long parentId = Long.valueOf(lastMsgId);
                Message parent = ServiceFactory.getService(MessageService.class).fetchById(Long.valueOf(parentId));
                ServiceFactory.getService(MessageService.class).replyMessage(parent, message);
            } else {
                ServiceFactory.getService(MessageService.class).save(message);
            }
            populateMessages(model);
            return "messageList";
        } else {
            populateMessages(model);
            return "messageList";
        }
    }
    
    private void retrieveMessageThread(Model model, HttpServletRequest request) {
        String msgId = request.getParameter("messageId");
        Message message = ServiceFactory.getService(MessageService.class).fetchById(Long.valueOf(msgId));
        if (message != null) {
            List<Message> messages = new ArrayList<Message>();
            messages.add(message);
            Message msg = message;
            do {
                if (msg.getReplyBy() != null) {
                    messages.add(msg.getReplyBy());
                    msg = msg.getReplyBy();
                }
            } while (msg != null && msg.getReplyBy() != null);
            Collections.reverse(messages);
            model.addAttribute("messageThread", messages);
        }
    }

    private Message populateMessage(HttpServletRequest request) {
        String content = request.getParameter("newMessage");
        String title = request.getParameter("messageTitle");
        Message msg = new Message();
        Date date = new Date();
        String dateStr = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date);
        msg.setContent(content);
        if (title != null) {
            msg.setName(title);
        } else {
            msg.setName(dateStr.trim());
        }
        msg.setCreateDate(date);
        msg.setReplied(false);
        msg.setUser(getUserView().getLoginUser());
        return msg;
    }
    
    private void populateMessages(Model model) {
        model.addAttribute("messages", ServiceFactory.getService(MessageService.class).getMessagesByUser(getUserView().getLoginUser()));
    }
}