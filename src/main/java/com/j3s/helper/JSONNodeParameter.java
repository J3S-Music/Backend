package com.j3s.helper;

public class JSONNodeParameter implements JSONNode {
    private final String name;
    private final int depth;
    private final String value;

    public String getValue() {
        return this.value;
    }

    public  JSONNodeParameter(String name, int depth, String value){
        this.name = name;
        this.depth = depth;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    public String toString(){
        return this.name+":"+this.value+" ("+this.depth+")\n";
    }

    public String toPrettyString(){
        return JSONTextHelper.currentOffset(this.depth)+this.name+":\""+this.value+"\"\n";
    }
}
