package co.yiiu.core.exception;

/**
 * Created by tomoya. Copyright (c) 2016, All Rights Reserved. https://yiiu.co
 */
public class ApiException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -5946678316928880226L;
	private int code;
	private String message;

	public ApiException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApiException(String message) {
		this.code = 201;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
