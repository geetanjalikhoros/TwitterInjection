package com.geetanjali.twtdw;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;
import java.util.List;

public class PostTweets {

    public void postStatus(String message, Twitter twitter) throws TwitterException
    {
        System.out.println("Status update successful to " + twitter.updateStatus(message).getText() + "\n");
    }
}
