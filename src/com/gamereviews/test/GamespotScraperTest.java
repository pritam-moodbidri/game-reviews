package com.gamereviews.test;

import com.gamereviews.model.GamespotReview;
import com.gamereviews.scraper.GamespotScraper;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GamespotScraperTest {

    private GamespotScraper gamespotScraper;

    @Before
    public void setUp() throws Exception {
        gamespotScraper = new GamespotScraper();
    }

    @Test
    public void testThatNullOrEmptyStringRequestsReturnNull() {
        assertNull(gamespotScraper.getReview(""));
        assertNull(gamespotScraper.getReview(" "));
        assertNull(gamespotScraper.getReview("    "));
        assertNull(gamespotScraper.getReview(null));
    }

    @Test
    public void testThatInvalidRequestsReturnNull() {
        assertNull(gamespotScraper.getReview("12345"));
        assertNull(gamespotScraper.getReview("20"));
        assertNull(gamespotScraper.getReview("!@#$"));
        assertNull(gamespotScraper.getReview("AaaBBBxx122##$@!"));
    }

    @Test
    public void testThatValidGameTitlesReturnPopulatedReviews() {
        GamespotReview review = gamespotScraper.getReview("fifa soccer 13");

        assertNotNull(review);
        assertNotNull(review.getGameTitle());
        assertNotNull(review.getGenre());
        assertNotNull(review.getReleaseDate());
        assertNotNull(review.getScorePS3());
        assertNotNull(review.getScoreXBOX360());
        assertNotNull(review.getScorePS_VITA());
        assertNull(review.getScorePC());
        assertNull(review.getScore3DS());
        assertNull(review.getScoreIOS());
    }

    @Test
    public void testThatGamesWithTitlesThatContainRomanNumeralsCanBeFoundByUsingRomanNumeralsOrDecimalNumbers() {
        GamespotReview reviewRetrievedByDecimalNumber = gamespotScraper.getReview("call of duty black ops 2");
        GamespotReview reviewRetrievedByRomanNumeral = gamespotScraper.getReview("call of duty black ops II");

        assertNotNull(reviewRetrievedByDecimalNumber);
        assertNotNull(reviewRetrievedByRomanNumeral);
        assertEquals(reviewRetrievedByDecimalNumber.getGameTitle(), reviewRetrievedByRomanNumeral.getGameTitle());
    }

    @Test
    public void testThatGamesWithTitlesThatContainDecimalNumbersCanOnlyBeFoundByUsingDecimalNumbers() {
        GamespotReview reviewRetrievedByDecimalNumber = gamespotScraper.getReview("Portal 2");
        GamespotReview reviewRetrievedByRomanNumeral = gamespotScraper.getReview("Portal II");

        assertNotNull(reviewRetrievedByDecimalNumber);
        assertNull(reviewRetrievedByRomanNumeral);
    }

    @Test
    public void testThatIOSGamesCanBeScraped() {
        GamespotReview review = gamespotScraper.getReview("Temple Run");
        assertNotNull(review);
    }

}
