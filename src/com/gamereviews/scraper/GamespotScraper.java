package com.gamereviews.scraper;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gamereviews.model.GamespotReview;

public class GamespotScraper {
	private static final String  PLATFORM_PS3 = "PS3";
	private static final String  PLATFORM_XBOX360 = "Xbox 360";
	private static final String  PLATFORM_PC = "PC";
	private static final String  PLATFORM_WII_U = "WIIU";
	private static final String  PLATFORM_3DS = "3DS";
	private static final String  PLATFORM_PS_VITA = "VITA";
	private static final String  PLATFORM_WII = "WII";
	private static final String  PLATFORM_DS = "DS";
	private static final String  PLATFORM_PSP = "PSP";
	private static final String  PLATFORM_MAC = "MAC";
	private static final String  PLATFORM_IOS = "iPhone";
	private static final String  PLATFORM_ANDROID = "Android";
	
	private Document connectWithRomanNumerals(String urlGameTitle){
		urlGameTitle = ScraperUtil.getTitleWithRomanNumerals(urlGameTitle);
		
		if (urlGameTitle==null)
			return null;
		
		String url = generateURL(urlGameTitle);
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			return null;
		}
		
		return doc;
	}
	
	private String generateURL(String urlGameTitle){
		urlGameTitle = urlGameTitle.toLowerCase().trim();
		urlGameTitle = urlGameTitle.replaceAll("[^A-Za-z0-9 ]", "");
		urlGameTitle = urlGameTitle.replace(" ","-");
		
		if (urlGameTitle.trim().equals(""))
			return null;
		
		String url = "http://gamespot.com/";
		url += urlGameTitle + "/";
		return url;
	}
	
	private GamespotReview extractScore(Document doc, GamespotReview review) {
		Elements elements = doc.getElementsByAttributeValue("class", "scoreRow");
		for (Element element : elements){
			Elements reviewItems =  element.getElementsByTag("li");
			
			for (Element reviewItem : reviewItems){
				Elements platform = reviewItem.getElementsByAttributeValue("class", "label");
				Elements score =  reviewItem.getElementsByAttributeValue("class", "scoreText");
				if (platform.size() == 1 && score.size()==1){
					String platformLabel = platform.text().trim();
					String scoreValue = score.text().trim();
					
					if (!scoreValue.equals("N/A")){
						if (platformLabel.equals(PLATFORM_PS3))
							review.setScorePS3(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_XBOX360))
							review.setScoreXBOX360(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_PC))
							review.setScorePC(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_WII_U))
							review.setScoreWII_U(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_3DS))
							review.setScore3DS(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_PS_VITA))
							review.setScorePS_VITA(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_WII))
							review.setScoreWII(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_DS))
							review.setScoreDS(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_PSP))
							review.setScorePSP(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_MAC))
							review.setScoreMAC(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_IOS))
							review.setScoreIOS(Double.parseDouble(scoreValue));
						else if (platformLabel.equals(PLATFORM_ANDROID))
							review.setScoreANDROID(Double.parseDouble(scoreValue));
					}
				}
			}
		}
		return review;
	}
	
	private GamespotReview extractGameMetadata(Document doc, GamespotReview review){
		
		Elements titleElements = doc.getElementsByAttributeValue("itemprop", "name");
		if (titleElements.size()>0 && titleElements.get(0).text().trim()!=null)
			review.setGameTitle(titleElements.get(0).text().trim());
		
		Elements genreElements = doc.getElementsByAttributeValue("itemprop", "genre");
		if (genreElements.size()>0 && genreElements.text().trim()!=null)
			review.setGenre(genreElements.text().trim());
		
		Elements releaseDateElements = doc.getElementsByAttributeValue("pubdate", "pubdate");
		if (releaseDateElements.size()>0 && releaseDateElements.get(0).text().trim()!=null)
			review.setReleaseDate(new Date(releaseDateElements.get(0).text().trim()));
		
		return review;
	}
	
	/**
	 * Returns reviews for a given game title from gamespot.com
	 * @param gameTitle The title of the game for review lookup
	 * @return GamespotReview if the game exists for the given title. Returns null if the game 
	 * doesn't exist or title is incorrect.
	 */
	
	public GamespotReview getReview(String gameTitle){
		GamespotReview review = new GamespotReview();
		
		if (gameTitle==null || gameTitle.trim().equals("")){
			return null;
		}
		
		String url = generateURL(gameTitle);
		if (url==null){
			return null;
		}
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			// Try the url with roman numerals
			doc = connectWithRomanNumerals(gameTitle);
		}
		
		if (doc != null){
			review = extractGameMetadata(doc, review);
			review = extractScore(doc, review);
			return review;
		}else{
			// Game not found with the given title
			return null;
		}
		
	}
		
}
