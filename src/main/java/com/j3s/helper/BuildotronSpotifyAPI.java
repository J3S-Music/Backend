package com.j3s.helper;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Creates a uri from userQ.json object. See demoUserQ for syntax
 */
public class BuildotronSpotifyAPI {
    /**
     * default spotify API base URI
     */
    private String baseURI ="https://api.spotify.com";

    /**
     * default constructor
     */
    public BuildotronSpotifyAPI(){}

    /**
     * base constructor that overwrites baseURI
     * @param baseURI the new baseURI
     */
    public BuildotronSpotifyAPI(String baseURI){
        this();
        this.baseURI = baseURI;
    }

    /**
     * Public call for JSONObject -> URI-String
     * uses {@link #qFromObject(JSONObject)}
     * @param userQ The userQ object from frontend
     * @return the search URI-String
     */
    public String generateQ(HashMap userQ){
        StringBuilder q = new StringBuilder(this.baseURI);
        q.append("/v1/search?");
        if(userQ.containsKey("q")){
            System.out.println(userQ.getClass().getName());
            HashMap qJSON = (HashMap) userQ.get("q");
            q.append(qFromObject(qJSON));
        }else{
            return null; //TODO errorhandling
        }
        q.append("&type=");
        ArrayList typeArray = (ArrayList) userQ.get("type");
        for(Object o : typeArray){
            q.append((String) o);
            q.append(",");
        }
        q.deleteCharAt(q.length()-1);
        return q.toString();
    }

    /**
     * creates the q part from a JSONObject
     * @param qJSON q Subobject from the userQ object from frontend
     * @return the q part of the URI
     */
    public String qFromObject(HashMap qJSON){
        StringBuilder q = new StringBuilder("q=");
        if(qJSON.containsKey("track")){
            q.append("track:");
            q.append(URLEncoder.encode((String) qJSON.get("track"),UTF_8));
            q.append("+");
        }
        if(qJSON.containsKey("artist")){
            q.append("artist:");
            q.append(URLEncoder.encode((String) qJSON.get("artist"),UTF_8));
            q.append("+");
        }
        if(qJSON.containsKey("album")){
            q.append("album:");
            q.append(URLEncoder.encode((String) qJSON.get("album"),UTF_8));
            q.append("+");
        }
        q.deleteCharAt(q.length()-1);
        return q.toString();
    }
}
