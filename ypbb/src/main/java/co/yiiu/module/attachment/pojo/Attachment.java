package co.yiiu.module.attachment.pojo;

import java.util.Date;

public class Attachment {
	private String fileName;

	private Integer height;

	private Integer id;

	private Date inTime;

	private String localPath;

	private String md5;

	private String requestUrl;

	private String size;

	private String suffix;

	private String type;

	private Integer width;

	public String getFileName() {
		return fileName;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getId() {
		return id;
	}

	public Date getInTime() {
		return inTime;
	}

	public String getLocalPath() {
		return localPath;
	}

	public String getMd5() {
		return md5;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public String getSize() {
		return size;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getType() {
		return type;
	}

	public Integer getWidth() {
		return width;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath == null ? null : localPath.trim();
	}

	public void setMd5(String md5) {
		this.md5 = md5 == null ? null : md5.trim();
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl == null ? null : requestUrl.trim();
	}

	public void setSize(String size) {
		this.size = size == null ? null : size.trim();
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix == null ? null : suffix.trim();
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
}