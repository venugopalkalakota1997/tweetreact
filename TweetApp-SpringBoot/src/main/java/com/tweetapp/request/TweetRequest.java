package com.tweetapp.request;

import com.tweetapp.dto.TweetsDto;

import lombok.Getter;
import lombok.Setter;


public class TweetRequest {

	public TweetsDto getTweet() {
		return tweet;
	}

	public void setTweet(TweetsDto tweet) {
		this.tweet = tweet;
	}

	private TweetsDto tweet;
}
