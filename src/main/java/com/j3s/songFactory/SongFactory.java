package com.j3s.songFactory;

import com.j3s.helper.GraphForSpotifySkimmer;
import com.j3s.helper.JSONNode;
import com.j3s.helper.JSONNodeArray;
import com.j3s.helper.JSONNodeParameter;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class SongFactory {
    private static final String[] keyWords = {"album","artists"};

    public static ArrayList<Song> generateSongs(GraphForSpotifySkimmer spotifyResponse){
        ArrayList<Song> songs = new ArrayList<>();
        JSONNode items = spotifyResponse.getSongArrayFromGraph();
        int resultCount = items.getChildCount();
        for(int i = 0; i<resultCount;i++) {
            ArrayList<JSONNode> nameNodes = items.findAllNodes("name");
            LinkedHashMap<String, String> nameDataMap = generateHashMap(nameNodes);
            Set<String> nameKeySet = nameDataMap.keySet();
            ArrayList<String> parameterArray = findAllParameters(nameKeySet, String.valueOf(i));
            JSONObject jSong = new JSONObject();
            for (String parameter : parameterArray) {
                String path = nameDataMap.get(parameter);
                String[] pathParts = path.split(":");
                if (pathParts.length > 7) {
                    continue;
                }
                String reference = "";
                for (String part : pathParts) {
                    if (isKeyWord(part)) {
                        reference = part;
                        break;
                    }
                }
                switch (reference) {
                    case "album": {
                        jSong.put("album", ((JSONNodeParameter) spotifyResponse.getNode(path)).getValue());
                        break;
                    }
                    case "artists": {
                        jSong.put("artist", ((JSONNodeParameter) spotifyResponse.getNode(path)).getValue());
                        break;
                    }
                    case "": {
                        jSong.put("track", ((JSONNodeParameter) spotifyResponse.getNode(path)).getValue());
                        break;
                    }
                }
            }
            ArrayList<JSONNode> idNodes = items.findAllNodes("id");
            LinkedHashMap<String, String> idDataMap = generateHashMap(idNodes);
            Set<String> idKeySet = idDataMap.keySet();
            parameterArray = findAllParameters(idKeySet, String.valueOf(i));
            for (String parameter : parameterArray) {
                String path = idDataMap.get(parameter);
                String[] pathParts = path.split(":");
                if (pathParts.length > 7) {
                    continue;
                }
                String reference = "";
                for (String part : pathParts) {
                    if (isKeyWord(part)) {
                        reference = part;
                        break;
                    }
                }
                if (reference.equals("")) {
                    jSong.put("trackUID", ((JSONNodeParameter) spotifyResponse.getNode(path)).getValue());
                }
            }

            jSong.put("imageInformation", new JSONObject());
            songs.add(new Song(jSong));
        }
        return songs;
    }

    public static LinkedHashMap<String, String> generateHashMap(ArrayList<JSONNode> nodePaths){
        LinkedHashMap<String, String> dataMap = new LinkedHashMap<>();
        for(JSONNode node : nodePaths){
            String fullPath = node.getPath();
            String[] split = fullPath.split("items");
            String infoPath = split[1];
            infoPath = infoPath.substring(1);
            dataMap.put(infoPath,fullPath);
        }
        return dataMap;
    }

    public static ArrayList<String> findAllParameters(Set<String> keySet, String regex){
        ArrayList<String> parameterKeys = new ArrayList<>();
        for(String s : keySet){
            String[] split = s.split(":");
            if(split[0].equals(regex)){
                parameterKeys.add(s);
            }
        }
        return parameterKeys;
    }

    private static boolean isKeyWord(String test){
        for(String key : keyWords){
            if(key.equals(test)){
                return true;
            }
        }
        return false;
    }
}
