package com.gamereviews.model;

import java.io.Serializable;
import java.util.Date;

public class MetacriticReview implements Serializable {
	String gameTitle;
	String genre;
	String publisher;
	Date releaseDate;
	Double scorePS3;
	Double scoreXBOX360;
	Double scorePC;
	Double scoreWII_U;
	Double score3DS;
	Double scorePS_VITA;
	Double scoreIOS;
	Double scoreWII;
	Double scoreDS;
	Double scorePSP;
	
	public String getGameTitle() {
		return gameTitle;
	}
	
	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public Double getScorePS3() {
		return scorePS3;
	}
	
	public void setScorePS3(Double scorePS3) {
		this.scorePS3 = scorePS3;
	}
	
	public Double getScoreXBOX360() {
		return scoreXBOX360;
	}
	
	public void setScoreXBOX360(Double scoreXBOX360) {
		this.scoreXBOX360 = scoreXBOX360;
	}
	
	public Double getScorePC() {
		return scorePC;
	}
	
	public void setScorePC(Double scorePC) {
		this.scorePC = scorePC;
	}
	
	public Double getScoreWII_U() {
		return scoreWII_U;
	}
	
	public void setScoreWII_U(Double scoreWII_U) {
		this.scoreWII_U = scoreWII_U;
	}
	
	public Double getScore3DS() {
		return score3DS;
	}
	
	public void setScore3DS(Double score3ds) {
		score3DS = score3ds;
	}
	
	public Double getScorePS_VITA() {
		return scorePS_VITA;
	}
	
	public void setScorePS_VITA(Double scorePS_VITA) {
		this.scorePS_VITA = scorePS_VITA;
	}
	
	public Double getScoreIOS() {
		return scoreIOS;
	}
	
	public void setScoreIOS(Double scoreIOS) {
		this.scoreIOS = scoreIOS;
	}
	
	public Double getScoreWII() {
		return scoreWII;
	}
	
	public void setScoreWII(Double scoreWII) {
		this.scoreWII = scoreWII;
	}
	
	public Double getScoreDS() {
		return scoreDS;
	}
	
	public void setScoreDS(Double scoreDS) {
		this.scoreDS = scoreDS;
	}
	
	public Double getScorePSP() {
		return scorePSP;
	}
	
	public void setScorePSP(Double scorePSP) {
		this.scorePSP = scorePSP;
	}
	
}
