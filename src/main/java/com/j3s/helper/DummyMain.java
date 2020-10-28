package com.j3s.helper;

import com.j3s.SpotifyDsl.SpotifyDsl;
import com.j3s.songFactory.Song;
import com.j3s.songFactory.SongFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//TODO Kill this soon!!
public class DummyMain {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
//        BuildotronSpotifyAPI b = new BuildotronSpotifyAPI();

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("./src/main/resources/demoUserQ.json");
        Object obj = jsonParser.parse(reader);
        JSONObject content = (JSONObject) obj;

       // GraphForSpotifySkimmer j = new GraphForSpotifySkimmer(content, "spotifyResponse");
        //System.out.println(j.toPrettyString());
/*        j.testFindAll("name");
        ArrayList<Song> songs =SongFactory.generateSongs(j);
        for(Song song : songs) {
            System.out.println(song.toJSong().toString());
        }
        System.out.println(j.getNode("spotifyResponse:tracks:items").getSubStructure());
//        System.out.println(j.toPrettyString());
        SpotifyLinkMock spotifyLinkMock = new SpotifyLinkMock("MmUxMDk1MGQzMWU5NGQ2NmFhOTQxZWM1NTY5ODJjOWM6MjFhMzI0MjVlYmNjNDVhMjg1ZTRkOWYzYWMzZjYwYTg=");
        System.out.println(spotifyLinkMock.getCurrentBearer());

        System.out.println(spotifyLinkMock.getCurrentBearer());*/
        System.out.println((new SpotifyDsl("MmUxMDk1MGQzMWU5NGQ2NmFhOTQxZWM1NTY5ODJjOWM6MjFhMzI0MjVlYmNjNDVhMjg1ZTRkOWYzYWMzZjYwYTg=")).findSongList(content));
    }
}
