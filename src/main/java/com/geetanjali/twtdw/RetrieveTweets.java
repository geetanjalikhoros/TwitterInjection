package com.geetanjali.twtdw;

import twitter4j.*;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class RetrieveTweets
{
    private List<Status> statuses;

    public RetrieveTweets()
    {
        this.statuses = new ArrayList<Status>();
    }

    public List<String> getStatus(Twitter twitter) throws TwitterException
    {
        Paging page = new Paging(1,200);
        statuses.addAll(twitter.getHomeTimeline(page));
        int count = statuses.size();
        List<String> str = new ArrayList<String>();
        while(count > 0)
        {
            count--;
            System.out.println("Tweet "+count+"="+statuses.get(count).getText());
            str.add(statuses.get(count).getText());
        }
        return str;
    }
}
