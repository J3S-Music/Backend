package com.j3s.helper;

import com.j3s.songFactory.Song;
import com.j3s.songFactory.SongFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DummyMain {
    public static void main(String[] args) throws IOException, ParseException {
//        BuildotronSpotifyAPI b = new BuildotronSpotifyAPI();

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("./src/main/resources/demoResponse.json");
        Object obj = jsonParser.parse(reader);
        JSONObject content = (JSONObject) obj;
        GraphForSpotifySkimmer j = new GraphForSpotifySkimmer(content, "spotifyResponse");
//        j.testFindAll("name");
        ArrayList<Song> songs =SongFactory.generateSongs(j);
        for(Song song : songs) {
            System.out.println(song.toJSong().toString());
        }
//        System.out.println(j.getNode("spotifyResponse:tracks:items").getSubStructure());
//        System.out.println(j.toPrettyString());
    }
}
