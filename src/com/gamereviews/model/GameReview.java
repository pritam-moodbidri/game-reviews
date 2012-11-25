package com.gamereviews.model;

import java.io.Serializable;

public class GameReview implements Serializable {
	GamespotReview gamespotReview;
	MetacriticReview metacriticReview;

	public MetacriticReview getMetacriticReview() {
		return metacriticReview;
	}

	public void setMetacriticReview(MetacriticReview metacriticReview) {
		this.metacriticReview = metacriticReview;
	}

	public GamespotReview getGamespotReview() {
		return gamespotReview;
	}

	public void setGamespotReview(GamespotReview gamespotReview) {
		this.gamespotReview = gamespotReview;
	}
}
