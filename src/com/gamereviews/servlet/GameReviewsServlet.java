package com.gamereviews.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamereviews.GameReviews;
import com.gamereviews.model.GameReview;
import com.gamereviews.model.GamespotReview;
import com.gamereviews.model.MetacriticReview;
import com.google.gson.Gson;

public class GameReviewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String reviewURI = "/game-reviews";
    private static final String metacriticURI = "/game-reviews/metacritic";
    private static final String gamespotURI = "/game-reviews/gamespot";
    
    public GameReviewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		GameReviews gameReviews = new GameReviews();
		String gameTitle = request.getParameter("title");
		if (gameTitle==null || gameTitle.trim().equals(""))
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Game title missing.");
		
		response.setStatus(response.SC_OK);
		if (request.getRequestURI().equals(reviewURI)){
			GameReview gameReview = gameReviews.getGameReview(gameTitle);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(gameReview));
		}
		
		else if (request.getRequestURI().equals(metacriticURI)){
			MetacriticReview metacriticReview = gameReviews.getMetacriticReview(gameTitle);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(metacriticReview));
		}
		
		else if (request.getRequestURI().equals(gamespotURI)){
			GamespotReview gamespotReview = gameReviews.getGamespotReview(gameTitle);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(gamespotReview));
		}
	}

}
