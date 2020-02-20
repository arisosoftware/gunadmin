package co.yiiu.config.properties;

/**
 * Created by tomoya at 2018/4/24
 */
public class QiniuConfig {

	private String accessKey;
	private String bucket;
	private String domain;
	private String secretKey;

	public String getAccessKey() {
		return accessKey;
	}

	public String getBucket() {
		return bucket;
	}

	public String getDomain() {
		return domain;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
