package com.j3s.playlistHandler;

import com.j3s.songFactory.Song;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * List with all potentially played songs. The songs can still be voted on.
 */
public class Playlist {
    private List<Song> playlist;
    //TODO Change to Room later?
    private String roomId;
    private final int upScale = 51;
    private final int downScale = 49;


    public Playlist(String roomId){
        this.roomId = roomId;
        this.playlist = new ArrayList<>();
    }

    public Playlist(String roomId, List<Song> startList){
        this(roomId);
        this.addAll(startList);
    }

    //Needs to tell frontend that user has upvoted because song is already in playlist
    public boolean add(Song song){
        for(Song existing : this.playlist){
            if(existing.getTrackUID().equals(song.getTrackUID())){
                existing.upVote(true);
                //Frontend info here
                return true;
            }
        }
        return this.playlist.add(song);
    }

    public boolean addAll(List<Song> songs){
        boolean result = true;
        for(Song song : songs){
            result = result && this.add(song);
        }
        return result;
    }

    public boolean remove(Song song){
        return this.playlist.remove(song);
    }

    public int evaluateSong(Song song){
        if(song==null){
            return 0;
        }
        int score = 0;
        score+=song.getUpVotes()*this.upScale;
        score-=song.getDownVotes()*this.downScale;
        return score;
    }

    //Change to JSONObject?
    public Song getNextSong(){
        Song next = null;
        for(Song song : this.playlist){
            if(evaluateSong(next)<evaluateSong(song)){
                next = song;
            }
        }
        if(next!=null) {
            this.playlist.remove(next);
        }
        return next/*toJSong()*/;
    }

    public JSONObject toPlaySON(){
        JSONObject play = new JSONObject();
        JSONArray items = new JSONArray();
        for(Song song : playlist){
            items.add(song.toJSong());
        }
        play.put("items",items);
        return play;
    }

    public Song findSong(String trackUID){
        for(Song song : this.playlist){
            if(song.getTrackUID().equals(trackUID)){
                return song;
            }
        }
        return null;
    }

    public boolean voteByJSON(JSONObject vote){
        Song toVote = this.findSong((String) vote.get("trackUID"));
        if((boolean) vote.get("vote")){
            return toVote.upVote((boolean) vote.get("voteStyle"));
        }
        if(!((boolean) vote.get("vote"))){
            return toVote.downVote((boolean) vote.get("voteStyle"));
        }
        return false;
    }
}
