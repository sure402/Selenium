package com.krishna.service.model;

/**
 * Class to manage Response POJO's. Since we don't have a way to map developer POJO's Hence created custom POJO's
 * 
 * @author kabothu 
 */

public class Results {

	private String vote_average;

	private String adult;

	private String id;

	private String title;

	private String original_language;

	private String overview;

	private String[] genre_ids;

	private String release_date;

	private String original_title;

	private String vote_count;

	private String poster_path;

	private String video;

	private String popularity;

	public String getVote_average() {
		return vote_average;
	}

	public void setVote_average(String vote_average) {
		this.vote_average = vote_average;
	}

	public String getAdult() {
		return adult;
	}

	public void setAdult(String adult) {
		this.adult = adult;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginal_language() {
		return original_language;
	}

	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String[] getGenre_ids() {
		return genre_ids;
	}

	public void setGenre_ids(String[] genre_ids) {
		this.genre_ids = genre_ids;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getVote_count() {
		return vote_count;
	}

	public void setVote_count(String vote_count) {
		this.vote_count = vote_count;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	@Override
	public String toString() {
		return "Results [vote_average = " + vote_average + ", adult = " + adult + ", id = " + id + ", title = " + title
				+ ", original_language = " + original_language + ", overview = " + overview + ", genre_ids = "
				+ genre_ids + ", release_date = " + release_date + ", original_title = " + original_title
				+ ", vote_count = " + vote_count + ", poster_path = " + poster_path + ", video = " + video
				+ ", popularity = " + popularity + "]";
	}
}