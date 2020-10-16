package com.j3s.helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JSONNodeObject implements JSONNode {
    private final String name;
    private final int depth;
    private List<JSONNode> children;
    private GraphForSpotifySkimmer daddy = null;

    public boolean addChild(JSONNode child){
        return this.children.add(child);
    }

    public JSONNode getChildByName(String name){
        for(JSONNode child : this.children){
            if(child.getName().equals(name)){
                return child;
            }
        }
        return null;
    }

    public JSONNodeObject(String name, int depth){
        this.name = name;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public JSONNodeObject(String name, int depth, JSONObject content){
        this(name,depth);
        this.createSubStructure(content);
    }

    public JSONNodeObject(String name, int depth, JSONObject content, GraphForSpotifySkimmer daddy){
        this(name,depth);
        this.daddy = daddy;
        this.createSubStructure(content);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    public void createSubStructure(JSONObject content){
        Set<String> keySet= content.keySet();
        for(String key : keySet){
            if(this.daddy!=null){
                if(this.daddy.checkIgnored(key)){
                    continue;
                }
            }
            Object child = content.get(key);
            String childType = "null";
            try {
                childType = child.getClass().getSimpleName();
            }catch(Exception ignored){}
            switch (childType) {
                case "JSONObject" -> {
                    JSONObject jChild = (JSONObject) child;
                    this.addChild(new JSONNodeObject(key, this.getDepth() + 1, jChild, this.daddy));
                    break;
                }
                case "JSONArray" -> {
                    JSONArray jChild = (JSONArray) child;
                    this.addChild(new JSONNodeArray(key, this.getDepth() + 1, jChild, this.daddy));
                    break;
                }
                case "null" -> {
                    this.addChild(new JSONNodeParameter(key, this.getDepth() + 1, "null"));
                    break;
                }
                default -> {
                    this.addChild(new JSONNodeParameter(key, this.getDepth() + 1, String.valueOf(child)));
                }
            }
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder(this.name+" ("+this.depth+")\n");
        for(JSONNode child : this.children) {
            s.append(child.toString());
        }
        return s.toString();
    }

    public String toPrettyString(){
        StringBuilder s = new StringBuilder(JSONTextHelper.currentOffset(this.depth));
        s.append(this.name+": {\n");
        for(JSONNode child : this.children) {
            s.append(child.toPrettyString());
        }
        s.append(JSONTextHelper.currentOffset(this.depth));
        s.append("}\n");
        return s.toString();
    }
}
