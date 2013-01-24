package com.spshop.model;

import com.spshop.model.enums.FeedBackType;

public class FeedBack extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9041684260070281865L;
	private String content;
	private String strType;
	private User user;
	public FeedBack() {
		// TODO Auto-generated constructor stub
	}
	public void setType(FeedBackType type) {
		this.strType = type.getValue();
	}
	
	public FeedBackType getType() {
		if(null==strType){
			return null;
		}
		return FeedBackType.valueOf(strType.toUpperCase());
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrType() {
		return strType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	/**
	 * @autogenerated by CodeHaggis (http://sourceforge.net/projects/haggis)
	 * clone
	 * @return Object
	 */
	public FeedBack clone() {
		FeedBack obj = null;
		obj = new FeedBack();
		if (this.content != null) {
			/* Does not have a clone() method */
			obj.content = this.content;
		}
		if (this.strType != null) {
			/* Does not have a clone() method */
			obj.strType = this.strType;
		}
		if (this.user != null) {
			obj.user = (User) this.user.clone();
		}
		return obj;
	}

}