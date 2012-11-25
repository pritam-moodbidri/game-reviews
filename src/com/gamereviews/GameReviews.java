package com.gamereviews;

import com.gamereviews.model.GameReview;
import com.gamereviews.model.GamespotReview;
import com.gamereviews.model.MetacriticReview;
import com.gamereviews.scraper.GamespotScraper;
import com.gamereviews.scraper.MetacriticScraper;

public class GameReviews {
	private GamespotScraper gamespotScraper;
	private MetacriticScraper metacriticScraper;
	
	public GameReviews(){
		gamespotScraper = new GamespotScraper();
		metacriticScraper = new MetacriticScraper();
	}
	
	public GameReview getGameReview(String gameTitle){
		GameReview review = new GameReview();
		
		GamespotReview gamespotReview = (gamespotScraper.getReview(gameTitle));
		review.setGamespotReview(gamespotReview);
		
		MetacriticReview metacriticReview = metacriticScraper.getReview(gameTitle);
		review.setMetacriticReview(metacriticReview);
		
		if (gamespotReview==null && metacriticReview==null)
			return null;
		else 
			return review;
	}
	
	public GamespotReview getGamespotReview(String gameTitle){
		GamespotReview review = gamespotScraper.getReview(gameTitle);
		return review;
	}
	
	public MetacriticReview getMetacriticReview(String gameTitle){
		MetacriticReview review = metacriticScraper.getReview(gameTitle);
		return review;
	}
	
	
}
