package com.j3s.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Specified version of {@link JSONGraph} used for spotify API response skimming/parsing
 */
public class GraphForSpotifySkimmer extends JSONGraph{
    /**
     * String list with parameters which are to be ignored. Performance optimization
     */
    public ArrayList<String> ignoreList;
    /**
     * Where to find said list
     */
    private final String FILE_NAME = "./src/main/resources/defaultIgnoreList.json";

    /**
     * Constructor that initializes the graph
     * @param content JSONObject from Spotify response
     * @param rootName name of the head node
     * @throws IOException
     * @throws ParseException
     */
    public GraphForSpotifySkimmer(JSONObject content,String rootName) throws IOException, ParseException {
        super();
        this.setIgnoreList(this.FILE_NAME);
        this.head = new JSONNodeObject(rootName, 0, content,this);
    }

    /**
     * Change the current ignoreList path
     * @param fileName Where to find the new list
     * @throws IOException
     * @throws ParseException
     */
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

    /**
     * find the first node with name "items" (contains the song elements)
     * @return said node
     */
    public JSONNode getSongArrayFromGraph(){
        return this.head.findFirstNode("items");
    }

    /**
     * find all nodes with certain key and print the parent directory
     * @param key said key
     */
    public void testFindAll(String key){
        ArrayList<JSONNode> r = this.head.findAllNodes(key);
        for(JSONNode node : r){
            System.out.println(node.getParentDir());
        }
    }

    /**
     * Check whether a key is to be ignored
     * @param testKey said key
     * @return true(ignore) false(do not ignore)
     */
    public boolean checkIgnored(String testKey){
        return this.ignoreList.contains(testKey);
    }

    /**
     * get Node by basePath
     * @param basePath path of the node
     * @return Found node (or null if nothing's found)
     */
    public JSONNode getNode(String basePath){
        String[] path = basePath.split(":");
        if(!path[0].equals(this.head.getName())){
            return null;
        }else{
            return this.head.findNode(path);
        }
    }
}
