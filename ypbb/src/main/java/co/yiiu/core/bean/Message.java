package co.yiiu.core.bean;

import java.util.Map;

/**
 * Created by tomoya at 2018/8/17
 */
public class Message {

	private Map<String, Object> payload;
	private String type;

	public Map<String, Object> getPayload() {
		return payload;
	}

	public String getType() {
		return type;
	}

	public void setPayload(Map<String, Object> payload) {
		this.payload = payload;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Message{" + "type='" + type + '\'' + ", payload=" + payload + '}';
	}
}
