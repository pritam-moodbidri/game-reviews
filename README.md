game-reviews
============

API for video game review, that scrapes reviews for video games on different platforms from Metacritic and GameSpot


- Developed on Eclipse Juno and used Java 1.6

- GameReviewsServlet.java is the main servlet, and returns one of the following three responses based on the url
	- Getting review from GameSpot (www.gamespot.com)
	- Getting review from Metacritic (www.gamespot.com)
	- Getting review from Metacritic and GameSpot
	
- Certain Game titles have roman numbers for their sequels. In case the user uses decimal numbers for a game title that has roman numerals, this API will do the decimal 
to roman numeral conversion to see if the game with that title exists
	
- Used Jsoup for parsing the web pages

- Used google-gson for JSON conversion

- This is the first version of this API, and more review website scrapers can be added to it along with further improvements

	