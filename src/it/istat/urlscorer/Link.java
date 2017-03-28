package it.istat.urlscorer;

/**
* @author  Donato Summa
*/
public class Link {

	private String firmId;
	private String linkPosition;
	private String url;
	private int scoreVector;
	private int score;
	
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	public String getLinkPosition() {
		return linkPosition;
	}
	public void setLinkPosition(String linkPosition) {
		this.linkPosition = linkPosition;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getScoreVector() {
		return scoreVector;
	}
	public void setScoreVector(int scoreVector) {
		this.scoreVector = scoreVector;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String toString() {
		return this.firmId + "\t" + this.linkPosition + "\t" + this.url + "\t" + this.scoreVector + "\t" + this.score;
	}
	
}
