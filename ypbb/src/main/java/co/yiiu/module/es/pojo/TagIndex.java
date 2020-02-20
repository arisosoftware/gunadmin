package co.yiiu.module.es.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by tomoya at 4/27/18
 */
@Document(indexName = "pybbs", type = "tag", refreshInterval = "-1")
public class TagIndex implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4587908458950849737L;
	@Id
	private Integer id;
	private Date inTime;
	private String intro;
	private String logo;
	private String name;
	private Integer topicCount;

	public Integer getId() {
		return id;
	}

	public Date getInTime() {
		return inTime;
	}

	public String getIntro() {
		return intro;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public Integer getTopicCount() {
		return topicCount;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}
}
