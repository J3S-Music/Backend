package com.j3s.helper;

import com.j3s.playlistHandler.Playlist;
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

//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = new FileReader("C:\\Users\\de5732\\git\\Backend\\src\\main\\resources\\demoResponse.json");
//        Object obj = jsonParser.parse(reader);
//        JSONObject content = (JSONObject) obj;
//        GraphForSpotifySkimmer j = new GraphForSpotifySkimmer(content, "spotifyResponse");
////        j.testFindAll("name");
//        ArrayList<Song> songs =SongFactory.generateSongs(j);
//        Playlist p = new Playlist("test");
//        p.addAll(songs);
//        reader = new FileReader("C:\\Users\\de5732\\git\\Backend\\src\\main\\resources\\demoVote.json");
//        obj = jsonParser.parse(reader);
//        JSONObject voteCorpus = (JSONObject) obj;
//        for(int i = 0;i<songs.size();i++){
//            String trackId = songs.get(i).getTrackUID();
//            voteCorpus.replace("trackUID",trackId);
//            if(i%2==0){
//                p.voteByJSON(voteCorpus);
//                p.voteByJSON(voteCorpus);
//            }else{
//                p.voteByJSON(voteCorpus);
//                p.voteByJSON(voteCorpus);
//                voteCorpus.replace("vote",false);
//                p.voteByJSON(voteCorpus);
//            }
//            p.add(songs.get(i));
//        }
//        System.out.println(p.getNextSong().getTrack());
//        System.out.println(j.getNode("spotifyResponse:tracks:items").getSubStructure());
//        System.out.println(j.toPrettyString());
        System.out.println(APIHelper.getBearerToken());
    }
}
