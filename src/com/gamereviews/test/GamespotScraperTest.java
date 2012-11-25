package com.gamereviews.test;

import org.junit.Assert;
import org.junit.Test;

import com.gamereviews.model.GamespotReview;
import com.gamereviews.scraper.GamespotScraper;

public class GamespotScraperTest {

	@Test
	public void testGetReview() {
		GamespotScraper gamespotScraper = new GamespotScraper();
		
		Assert.assertNull(gamespotScraper.getReview(""));
		Assert.assertNull(gamespotScraper.getReview(" "));
		Assert.assertNull(gamespotScraper.getReview("	"));
		Assert.assertNull(gamespotScraper.getReview(null));
		
		// Incorrect Game title
		Assert.assertNull(gamespotScraper.getReview("12345"));
		Assert.assertNull(gamespotScraper.getReview("20"));
		Assert.assertNull(gamespotScraper.getReview("!@#$"));
		Assert.assertNull(gamespotScraper.getReview("AaaBBBxx122##$@!"));
		
		// Correct Game title
		GamespotReview review;
		Assert.assertNotNull(review = gamespotScraper.getReview("fifa soccer 13"));
		Assert.assertNotNull(review.getGameTitle());
		Assert.assertNotNull(review.getGenre());
		Assert.assertNotNull(review.getReleaseDate());
		Assert.assertNotNull(review.getScorePS3());
		Assert.assertNotNull(review.getScoreXBOX360());
		Assert.assertNotNull(review.getScorePS_VITA());
		
		Assert.assertNotNull(gamespotScraper.getReview("call of duty black ops 2"));
		Assert.assertNotNull(gamespotScraper.getReview("call of duty black ops II"));
		Assert.assertNotNull(gamespotScraper.getReview("Portal 2"));
		Assert.assertNull(gamespotScraper.getReview("Portal II"));
		Assert.assertNotNull(gamespotScraper.getReview("Temple Run"));
	}

}
