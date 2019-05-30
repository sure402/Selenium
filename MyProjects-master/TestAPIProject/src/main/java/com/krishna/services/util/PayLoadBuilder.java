package com.krishna.services.util;

/**
 * Class to manage PayLoadBuilder. This will be very useful if we want to test with multiple combinations of payload
 * in one test script file.
 * 
 * @author kabothu 
 */

public class PayLoadBuilder {

	private String title;
	private String name;
	private String description;
	private String poster_path;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	private PayLoadBuilder(Builder builder) {
		this.title = builder.title;
		this.name = builder.name;
		this.description = builder.description;
		this.poster_path = builder.poster_path;

	}

	public static class Builder {

		String title;
		String name;
		String description;
		String poster_path;

		public Builder() {

		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder poster_path(String poster_path) {
			this.poster_path = poster_path;
			return this;
		}

		public PayLoadBuilder build() {
			return new PayLoadBuilder(this);
		}

	}

}
