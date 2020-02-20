package co.yiiu.core.bean;

/**
 * Created by tomoya. Copyright (c) 2016, All Rights Reserved. https://yiiu.co
 */
public class Result {

	public static Result error() {
		return Result.error(null);
	}

	public static Result error(int code, String description) {
		Result result = new Result();
		result.setCode(code);
		result.setDescription(description);
		result.setDetail(null);
		return result;
	}

	public static Result error(String description) {
		return Result.error(201, description);
	}

	public static Result success() {
		return Result.success(null);
	}

	public static Result success(Object detail) {
		Result result = new Result();
		result.setCode(200);
		result.setDescription("success");
		result.setDetail(detail);
		return result;
	}

	private int code;

	private String description;

	private Object detail;

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Object getDetail() {
		return detail;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}
}
