package com.gamereviews.test;

import org.junit.Assert;
import org.junit.Test;

import com.gamereviews.model.MetacriticReview;
import com.gamereviews.scraper.MetacriticScraper;

public class MetacriticScraperTest {

	@Test
	public void testGetReview() {
		MetacriticScraper metacriticScraper = new MetacriticScraper();
		
		
		Assert.assertNull(metacriticScraper.getReview(""));
		Assert.assertNull(metacriticScraper.getReview(" "));
		Assert.assertNull(metacriticScraper.getReview("	"));
		Assert.assertNull(metacriticScraper.getReview(null));
		
		// Incorrect Game title
		Assert.assertNull(metacriticScraper.getReview("12345"));
		Assert.assertNull(metacriticScraper.getReview("20"));
		Assert.assertNull(metacriticScraper.getReview("!@#$"));
		Assert.assertNull(metacriticScraper.getReview("AaaBBBxx122##$@!"));
		
		// Correct Game title
		MetacriticReview review;
		Assert.assertNotNull(review = metacriticScraper.getReview("fifa soccer 13"));
		Assert.assertNotNull(review.getGameTitle());
		Assert.assertNotNull(review.getGenre());
		Assert.assertNotNull(review.getReleaseDate());
		Assert.assertNotNull(review.getPublisher());
		Assert.assertNotNull(review.getScorePS3());
		Assert.assertNotNull(review.getScoreXBOX360());
		Assert.assertNotNull(review.getScorePC());
		Assert.assertNotNull(review.getScore3DS());
		Assert.assertNotNull(review.getScorePS_VITA());
		Assert.assertNotNull(review.getScoreIOS());
		
		Assert.assertNotNull(metacriticScraper.getReview("call of duty black ops 2"));
		Assert.assertNotNull(metacriticScraper.getReview("call of duty black ops II"));
		Assert.assertNotNull(metacriticScraper.getReview("Portal 2"));
		Assert.assertNull(metacriticScraper.getReview("Portal II"));
		Assert.assertNotNull(metacriticScraper.getReview("Temple Run"));
	}

}
