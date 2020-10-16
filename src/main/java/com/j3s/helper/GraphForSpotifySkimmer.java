package com.j3s.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphForSpotifySkimmer extends JSONGraph{
    public ArrayList<String> ignoreList;
    private final String fileName = "C:\\Users\\de5732\\git\\Backend\\src\\main\\resources\\defaultIgnoreList.json";


    public GraphForSpotifySkimmer(JSONObject content,String rootName) throws IOException, ParseException {
        super();
        this.setIgnoreList(this.fileName);
        this.head = new JSONNodeObject(rootName, 0, content,this);
    }

    private void setIgnoreList(String fileName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(fileName);
        Object obj = jsonParser.parse(reader);
        JSONArray content = (JSONArray) obj;
        this.ignoreList = new ArrayList<String>();
        for(Object o : content){
            this.ignoreList.add(o.toString());
        }
    }



    public boolean checkIgnored(String testKey){
        return this.ignoreList.contains(testKey);
    }
}
