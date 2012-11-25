package com.gamereviews.scraper;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gamereviews.model.MetacriticReview;
import com.google.gson.Gson;

public class MetacriticScraper{
	
	private enum Platform{
		PLATFORM_PS3("playstation-3"),
		PLATFORM_XBOX360("xbox-360"),
		PLATFORM_PC("pc"),
		PLATFORM_WII_U("wii-u"),
		PLATFORM_3DS("3ds"),
		PLATFORM_PS_VITA("playstation-vita"),
		PLATFORM_IOS("ios"),
		PLATFORM_WII("wii"),
		PLATFORM_DS("ds"),
		PLATFORM_PSP("psp");
		
		private String platform;
		
		private Platform(String platform){
			this.platform = platform;
		}
		
		public String getPlatformValue(){
			return this.platform;
		}
	}
	
	private String generateURL(String urlGameTitle, String platform){
		urlGameTitle = urlGameTitle.toLowerCase().trim();
		urlGameTitle = urlGameTitle.replaceAll("[^A-Za-z0-9 ]", "");
		urlGameTitle = urlGameTitle.replace(" ","-");
		
		if (urlGameTitle.trim().equals(""))
			return null;
		
		String url = "http://www.metacritic.com/game/";
		url += platform + "/";
		url += urlGameTitle;
		return url;
	}
	
	private Document loadDocument(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			return null;
		}
		
		if (doc != null){
			Elements errorCodeElement = doc.getElementsByAttributeValue("class", "error_code");
			if (errorCodeElement.size()!=0)
				return null;
		}
		
		return doc;
	}
	
	
	private MetacriticReview extractGameMetadata(MetacriticReview review, Document doc){
		
		if (doc != null){
			
			// Extract game title
			Elements titleDivElement = doc.getElementsByAttributeValue("class", "product_title");
			
			if (titleDivElement.size()==1){
				Elements titleElement = titleDivElement.get(0).getElementsByAttributeValue("class", "hover_none");
				if (titleElement.size()==1 && titleElement.get(0).text().trim()!=null)
					review.setGameTitle(titleElement.get(0).text().trim());
			}
			
			
			// Extract publisher name
			Elements publisherItemElement = doc.getElementsByAttributeValue("class", "summary_detail publisher");
			if (publisherItemElement.size()==1){
				Elements publisherElement = publisherItemElement.get(0).getElementsByTag("a");
				if (publisherElement.size()==1 && publisherElement.get(0).text().trim()!=null)
					review.setPublisher(publisherElement.get(0).text().trim());
			}
				
			// Extract Genre
			Elements genreItemElement = doc.getElementsByAttributeValue("class", "summary_detail product_genre");
			if (genreItemElement.size()==1){
				Elements genreElement = genreItemElement.get(0).getElementsByAttributeValue("class", "data");
				if (genreElement.size()==1 && genreElement.get(0).text().trim()!=null)
					review.setGenre(genreElement.get(0).text().trim());
			}
			
			// Extract Release Date
			Elements releaseItemElement = doc.getElementsByAttributeValue("class", "summary_detail release_data");
			if (releaseItemElement.size()==1){
				Elements releaseElement = releaseItemElement.get(0).getElementsByAttributeValue("class", "data");
				if (releaseElement.size()==1 && releaseElement.get(0).text().trim()!=null)
					review.setReleaseDate(new Date(releaseElement.get(0).text().trim()));
			}
			
		}
		
		return review;
	}
	
	private MetacriticReview extractScores(String urlGameTitle, MetacriticReview review){
		boolean convertedNumeralsToRoman = false;
		boolean updatedMetadate = false;
		
		// Make calls to different urls for different platforms to get review scores
		for (Platform platform : Platform.values()){
			Double score = null;
			
			String url = generateURL(urlGameTitle, platform.getPlatformValue());
			if (url==null)
				continue;
			
			Document doc = loadDocument(url);
			if (doc==null && !convertedNumeralsToRoman){
				String gameTitleWithRomanNumerals = ScraperUtil.getTitleWithRomanNumerals(urlGameTitle);
				if (gameTitleWithRomanNumerals==null)
					continue;
				
				url = generateURL(gameTitleWithRomanNumerals, platform.getPlatformValue());
				if (url==null)
					continue;
				
				doc = loadDocument(url);
				
				if (doc!=null){
					urlGameTitle = gameTitleWithRomanNumerals;
					convertedNumeralsToRoman = true;
				}
			}
			
			
			if (doc != null){
				//Update game metadata
				if (!updatedMetadate){
					review = extractGameMetadata(review, doc);
					updatedMetadate = true;
				}
				
				//Update score for current platform
				Elements scoreElement = doc.getElementsByAttributeValue("property", "v:average");
				if (scoreElement.size()==1 && scoreElement.get(0).text().trim()!=null)
					score = Double.parseDouble(scoreElement.get(0).text().trim());
				
				switch (platform){
					case PLATFORM_PS3:
						review.setScorePS3(score);
						break;
						
					case PLATFORM_XBOX360:
						review.setScoreXBOX360(score);
						break;
						
					case PLATFORM_PC:
						review.setScorePC(score);
						break;
						
					case PLATFORM_WII_U:
						review.setScoreWII_U(score);
						break;
						
					case PLATFORM_3DS:
						review.setScore3DS(score);
						break;
						
					case PLATFORM_PS_VITA:
						review.setScorePS_VITA(score);
						break;
						
					case PLATFORM_IOS:
						review.setScoreIOS(score);
						break;
						
					case PLATFORM_WII:
						review.setScoreWII(score);
						break;
						
					case PLATFORM_DS:
						review.setScoreDS(score);
						break;
						
					case PLATFORM_PSP:
						review.setScorePSP(score);
						break;
				}
			}
		}
		if (updatedMetadate)
			return review;
		else 
			return null;
	}
	
	/**
	 * Returns reviews for a given game title from metacritic.com
	 * @param gameTitle The title of the game for review lookup
	 * @return MetacriticReview if the game exists for the given title. Returns null if the game 
	 * doesn't exist or title is incorrect.
	 */
	
	public MetacriticReview getReview(String gameTitle){
		MetacriticReview review = new MetacriticReview();
		
		if (gameTitle==null || gameTitle.trim().equals(""))
			return null;
		
		review = extractScores(gameTitle, review);
		return review;
	}
}
