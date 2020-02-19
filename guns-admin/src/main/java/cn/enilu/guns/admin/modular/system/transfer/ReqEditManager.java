package cn.enilu.guns.admin.modular.system.transfer;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ReqEditManager {

	@NotNull
	private String userId;

	@NotNull
	private String userName;

	private String userPassword;

	@NotNull
	@Length(min = 11, max = 11)
	private String userPhone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}
