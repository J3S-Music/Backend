package com.j3s.helper;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public interface JSONNode {
    String getName();
    int getDepth();
    String toPrettyString();
    JSONNode findFirstNode(String key);
    ArrayList<JSONNode> findAllNodes(String key);
    String getParentDir();
    JSONNode findNode(String[] path);
}
