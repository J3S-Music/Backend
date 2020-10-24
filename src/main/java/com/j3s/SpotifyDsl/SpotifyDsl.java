package com.j3s.SpotifyDsl;

import com.j3s.helper.*;
import com.j3s.playlistHandler.Playlist;
import com.j3s.songFactory.Song;
import com.j3s.songFactory.SongFactory;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class SpotifyDsl {
    private SpotifyLinkMock spotifyAuth;
    private BuildotronSpotifyAPI buildy;
    private GraphForSpotifySkimmer skimmer;

    public SpotifyDsl(String clientAndSecret){
        this.spotifyAuth = new SpotifyLinkMock(clientAndSecret);
        this.buildy = new BuildotronSpotifyAPI();
    }

    public JSONObject findSongList(JSONObject userQ) throws IOException, ParseException {
        JSONObject playSong = null;
        String  uri = this.buildy.generateQ(userQ);
        String[] urlSplit = uri.split("\\?");
        RequestSpecification req = APIHelper.getNewSongQ(new User(),urlSplit[0],spotifyAuth.getCurrentBearer());
        Response response1 = req.get("?"+urlSplit[1]);
        ResponseBody body = response1.getBody();
        JSONParser parser = new JSONParser();
        JSONObject res1 = (JSONObject) parser.parse(body.asString());
        skimmer = new GraphForSpotifySkimmer(res1,"spotifyResponse");
        System.out.println(skimmer.toPrettyString());
        ArrayList<Song> songs = SongFactory.generateSongs(skimmer);
        Playlist res = new Playlist("roomIdWhyTheFuck",songs);
        return res.toPlaySON();
    }
}
