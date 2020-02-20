package co.yiiu.module.es.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by tomoya at 2018/4/24
 */
@Document(indexName = "pybbs", type = "topic", refreshInterval = "-1")
public class TopicIndex implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8368995228257600204L;

	@Field(analyzer = "ik_max_word", type = FieldType.text)
	private String content;

	@Id
	private Integer id;

	private Date inTime;
	private String tag;
	@Field(analyzer = "ik_max_word", type = FieldType.text)
	private String title;
	private String username;
	private Double weight;

	public TopicIndex() {
	}

	public TopicIndex(Integer id, String title, String content, String username, String tag, Date inTime,
			Double weight) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.username = username;
		this.tag = tag;
		this.inTime = inTime;
		this.weight = weight;
	}

	public String getContent() {
		return content;
	}

	public Integer getId() {
		return id;
	}

	public Date getInTime() {
		return inTime;
	}

	public String getTag() {
		return tag;
	}

	public String getTitle() {
		return title;
	}

	public String getUsername() {
		return username;
	}

	public Double getWeight() {
		return weight;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
