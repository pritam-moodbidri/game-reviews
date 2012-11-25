package com.gamereviews.scraper;

public class ScraperUtil {

	private static String convertNumberstoRomanNumerals(int input){
		String romanValue = "";

		if (input > 3999 || input < 1)
		 return null;


		while (input > 999) {
		  romanValue = romanValue + "m"; 
		  input = input - 1000;
		}

		if (input > 899) {
		  romanValue = romanValue + "cm";
		  input = input - 900;
		}

		if (input > 499) {
		  romanValue = romanValue + "d";
		  input = input - 500;
		}

		if (input > 399) {
		  romanValue = romanValue + "cd";
		  input = input - 400;
		}

		while (input > 99) {
		  romanValue = romanValue + "c";
		  input = input - 100;
		}

		if (input > 89) {
		  romanValue = romanValue + "xc";
		  input = input - 90;
		}

		if (input > 49) {
		  romanValue = romanValue + "l";
		  input = input - 50;
		}

		if (input > 39) {
		  romanValue = romanValue + "xl";
		  input = input - 40;
		}

		while (input > 9) {
		  romanValue = romanValue + "x";
		  input = input - 10;
		}

		if (input > 8) {
		  romanValue = romanValue + "ix";
		  input = input - 9;
		}

		if (input > 4) {
		  romanValue = romanValue + "v";
		  input = input - 5;
		}

		if (input > 3) {
		  romanValue = romanValue + "iv";
		  input = input - 4;
		}

		while (input > 0) {
		  romanValue = romanValue + "i";
		  input = input - 1;
		}

		return romanValue;

	}
	
	/**
	 * Returns the game title having all the numbers converted into Roman numerals
	 * @param gameTitle The title that needs numeral conversion
	 * @return Game title with the Roman numeral conversion. Returns null if the title doesn't have 
	 * any numbers. Returns null if the number in the title is greater than 3999.
	 */
	
	public static String getTitleWithRomanNumerals(String gameTitle){
		String numbers =  gameTitle.replaceAll("[^0-9 ]", "").trim();
		if (numbers==null || numbers.trim().equals(""))
			return null;
		
		String romanValue = convertNumberstoRomanNumerals(Integer.parseInt(numbers));
		
		if (romanValue==null){
			return null;
		}
		else{
			gameTitle = gameTitle.replaceAll(numbers, romanValue);
			return gameTitle;
		}
	}
}
