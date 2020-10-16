package com.j3s.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONGraph {
    protected JSONNode head;

    public JSONGraph(){};

    public JSONGraph(JSONObject content, String rootName){
        this.head = new JSONNodeObject(rootName, 0, content);
    }


    public JSONGraph(JSONArray content, String rootName){
        this.head = new JSONNodeArray(rootName, 0, content);
    }

    public String toString(){
        return this.head.toString();
    }

    public String toPrettyString(){
        return this.head.toPrettyString();
    }
}
