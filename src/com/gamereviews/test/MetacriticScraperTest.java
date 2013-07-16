package com.gamereviews.test;

import com.gamereviews.model.MetacriticReview;
import com.gamereviews.scraper.MetacriticScraper;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MetacriticScraperTest {

    private MetacriticScraper metacriticScraper;

    @Before
    public void setUp() throws Exception {
        metacriticScraper = new MetacriticScraper();
    }

    @Test
    public void testThatNullOrEmptyStringRequestsReturnNull() {
        assertNull(metacriticScraper.getReview(""));
        assertNull(metacriticScraper.getReview(" "));
        assertNull(metacriticScraper.getReview("	"));
        assertNull(metacriticScraper.getReview(null));
    }

    @Test
    public void testThatInvalidGameTitlesReturnNull() {
        assertNull(metacriticScraper.getReview("12345"));
        assertNull(metacriticScraper.getReview("20"));
        assertNull(metacriticScraper.getReview("!@#$"));
        assertNull(metacriticScraper.getReview("AaaBBBxx122##$@!"));
    }

    @Test
    public void testThatValidGameTitlesReturnPopulatedReviews() {
        MetacriticReview review;
        assertNotNull(review = metacriticScraper.getReview("fifa soccer 13"));
        assertNotNull(review.getGameTitle());
        assertNotNull(review.getGenre());
        assertNotNull(review.getReleaseDate());
        assertNotNull(review.getPublisher());
        assertNotNull(review.getScorePS3());
        assertNotNull(review.getScoreXBOX360());
        assertNotNull(review.getScorePC());
        assertNotNull(review.getScore3DS());
        assertNotNull(review.getScorePS_VITA());
        assertNotNull(review.getScoreIOS());
    }

    @Test
    public void testThatGamesWithTitlesThatContainRomanNumeralsCanBeFoundByUsingRomanNumeralsOrDecimalNumbers() {
        MetacriticReview reviewRetrievedByDecimalNumber = metacriticScraper.getReview("call of duty black ops 2");
        MetacriticReview reviewRetrievedByRomanNumeral = metacriticScraper.getReview("call of duty black ops II");

        assertNotNull(reviewRetrievedByDecimalNumber);
        assertNotNull(reviewRetrievedByRomanNumeral);
        assertEquals(reviewRetrievedByDecimalNumber.getGameTitle(), reviewRetrievedByRomanNumeral.getGameTitle());
    }

    @Test
    public void testThatGamesWithTitlesThatContainDecimalNumbersCanOnlyBeFoundByUsingDecimalNumbers() {
        MetacriticReview reviewRetrievedByDecimalNumber = metacriticScraper.getReview("Portal 2");
        MetacriticReview reviewRetrievedByRomanNumeral = metacriticScraper.getReview("Portal II");

        assertNotNull(reviewRetrievedByDecimalNumber);
        assertNull(reviewRetrievedByRomanNumeral);
    }

    @Test
    public void testThatIOSGameRatingsCanBeScraped() {
        MetacriticReview review = metacriticScraper.getReview("Temple Run");
        assertNotNull(review);
    }
}
