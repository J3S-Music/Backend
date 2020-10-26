package com.j3s.helper;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URLEncoder;
import static java.nio.charset.StandardCharsets.UTF_8;

public class BuildotronSpotifyAPI {

    private String baseURI ="https://api.spotify.com";

    public BuildotronSpotifyAPI(){}

    public BuildotronSpotifyAPI(String baseURI){
        this();
        this.baseURI = baseURI;
    }

    public String generateQ(JSONObject userQ){
        StringBuilder q = new StringBuilder(this.baseURI);
        q.append("/v1/search?");
        if(userQ.containsKey("q")){
            JSONObject qJSON = (JSONObject) userQ.get("q");
            q.append(qFromObject(qJSON));
        }else{
            return null; //TODO errorhandling
        }
        q.append("&type=");
        JSONArray typeArray = (JSONArray) userQ.get("type");
        for(Object o : typeArray){
            q.append((String) o);
            q.append(",");
        }
        q.deleteCharAt(q.length()-1);
        return q.toString();
    }

    public String qFromObject(JSONObject qJSON){
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
