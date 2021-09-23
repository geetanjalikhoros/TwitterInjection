package com.geetanjali.twtdw.resources;

import com.codahale.metrics.annotation.Metered;
import com.geetanjali.twtdw.PostTweets;
import com.geetanjali.twtdw.RetrieveTweets;
import com.geetanjali.twtdw.api.Representation;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import java.util.logging.Logger;
import org.slf4j.Marker;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    private final String message;
    private RetrieveTweets twt;
    private PostTweets ptwt;
    private Twitter twitter;

    public Resource(String message) {
        this.message = message;
        this.twitter = TwitterFactory.getSingleton();
        this.twt = new RetrieveTweets();
        this.ptwt = new PostTweets();
    }

    @Path("/timeline")
    @GET
    public Response fetchTweet()
    {
        try
        {
            List<String> str = twt.getStatus(twitter);
            return Response.ok().entity(str).build();
        }
        catch (TwitterException e)
        {
            return Response.serverError().entity("Error in retrieving tweets").build();
        }
    }

    @Path("/tweet")
    @POST
    public Response postTweet()
    {
        try
        {
            ptwt.postStatus(message,twitter);
            return Response.ok().entity("Status updated successfully").build();
        }
        catch (TwitterException e)
        {
            return Response.serverError().entity("Error in updating status").build();
        }
    }
}