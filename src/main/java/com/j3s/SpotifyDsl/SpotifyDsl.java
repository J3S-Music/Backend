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
    private static SpotifyLinkMock spotifyAuth = new SpotifyLinkMock("");
    private static BuildotronSpotifyAPI buildy = new BuildotronSpotifyAPI();
    private static GraphForSpotifySkimmer skimmer;

    /*public SpotifyDsl(String clientAndSecret){
        this.spotifyAuth = new SpotifyLinkMock(clientAndSecret);
        this.buildy = new BuildotronSpotifyAPI();
    }*/

    public static Playlist findSongList(JSONObject userQ) throws IOException, ParseException {
        String  uri = buildy.generateQ(userQ);
        String[] urlSplit = uri.split("\\?");

        RequestSpecification songQ = APIHelper.getNewSongQ(new User(),urlSplit[0],spotifyAuth.getCurrentBearer());
        Response response = songQ.get("?"+urlSplit[1]);
        ResponseBody body = response.getBody();
        JSONParser parser = new JSONParser();
        JSONObject responseJSON = (JSONObject) parser.parse(body.asString());
        skimmer = new GraphForSpotifySkimmer(responseJSON,"spotifyResponse");
        ArrayList<Song> songs = SongFactory.generateSongs(skimmer);
        Playlist res = new Playlist("roomIdWhyTheFuck",songs);
        //return res.toPlaySON();
        return res;
    }
}
