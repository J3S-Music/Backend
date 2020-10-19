package com.j3s.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphForSpotifySkimmer extends JSONGraph{
    public ArrayList<String> ignoreList;
    private final String FILE_NAME = "C:\\Users\\de5732\\git\\Backend\\src\\main\\resources\\defaultIgnoreList.json";


    public GraphForSpotifySkimmer(JSONObject content,String rootName) throws IOException, ParseException {
        super();
        this.setIgnoreList(this.FILE_NAME);
        this.head = new JSONNodeObject(rootName, 0, content,this);
    }

    private void setIgnoreList(String fileName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(fileName);
        Object obj = jsonParser.parse(reader);
        JSONArray content = (JSONArray) obj;
        this.ignoreList = new ArrayList<>();
        for(Object o : content){
            this.ignoreList.add(o.toString());
        }
    }

    public JSONNode getSongArrayFromGraph(){
        return this.head.findFirstNode("items");
    }

    public void testFindAll(String key){
        ArrayList<JSONNode> r = this.head.findAllNodes(key);
        for(JSONNode node : r){
            System.out.println(node.getParentDir());
        }
    }

    public boolean checkIgnored(String testKey){
        return this.ignoreList.contains(testKey);
    }

    public JSONNode getNode(String basePath){
        String[] path = basePath.split(":");
        if(!path[0].equals(this.head.getName())){
            return null;
        }else{
            return this.head.findNode(path);
        }
    }
}
