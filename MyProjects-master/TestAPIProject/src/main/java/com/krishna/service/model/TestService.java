package com.krishna.service.model;


/**
 * Class to manage POJO's. Since we don't have a way to map developer POJO's Hence created custom POJO's
 * 
 * @author kabothu 
 */
public class TestService {
	private String description;

	private String name;
	
	private String count;
	
	private String expectedResponseCode;
	
	private String title;
	
	private String poster_path;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getExpectedResponseCode() {
		return expectedResponseCode;
	}

	public void setExpectedResponseCode(String expectedResponseCode) {
		this.expectedResponseCode = expectedResponseCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	@Override
	public String toString() {
		return "[description = " + description + ", name = " + name + "]";
	}
}
