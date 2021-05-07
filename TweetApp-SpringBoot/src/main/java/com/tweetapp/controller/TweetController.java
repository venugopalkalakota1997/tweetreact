package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.kafka.Producer;
import com.tweetapp.request.TweetRequest;
import com.tweetapp.response.TweetResponse;
import com.tweetapp.service.TweetsService;

@RestController
public class TweetController {

	@Autowired
	TweetsService tweetsService;
	
	public Producer producer;

    @Autowired
    TweetController(Producer producer) {
        this.producer = producer;
    }

	@GetMapping(value = "/api/v1.0/tweets/all")
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse getAllTweets() {
		return tweetsService.getAllTweets();
	}

	@GetMapping(value = "/api/v1.0/tweets/{username}" )
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse getAllTweetsUser(@PathVariable("username") String userName) {
		return tweetsService.getAllTweetsByUserName(userName);
		
	}
	
	@PostMapping(value = "/api/v1.0/tweets/{username}/add" )
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse addTweet(@RequestBody TweetRequest request , @PathVariable("username") String userName ) {
		 this.producer.sendMessage(request.getTweet().getTweet());
		return tweetsService.addTweet(request, userName);	
	}
	
	@RequestMapping(path = "/api/v1.0/tweets/{username}/delete/{id}" , method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse deleteTweet(@PathVariable("username") String userName, @PathVariable("id") Long tweetId) {
		return tweetsService.deleteTweet(userName,tweetId);
		
	}
	
	@PostMapping(value = "/api/v1.0/tweets/reply" )
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse replyToTweet(@RequestBody TweetRequest request ) {
		
		 this.producer.sendMessage(request.getTweet().getReply().get(0).getReplied());
		return tweetsService.replyToTweet(request);	
	}
	
	@RequestMapping(value="/api/v1.0/tweets/like",method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse likeATweet(@RequestBody  TweetRequest request ) {
		
		return tweetsService.likeATweet(request);
	}
	
	@RequestMapping(value="/api/v1.0/tweets/update",method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public TweetResponse updateTweet(@RequestBody  TweetRequest request ) {
		 this.producer.sendMessage(request.getTweet().getTweet());
		return tweetsService.updateTweet(request);
	}
	
	
}
