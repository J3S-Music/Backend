package com.j3s.playlistHandler;

import com.j3s.songFactory.Song;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * List with all potentially played songs. The songs can still be voted on.
 * Also used to return search results to frontend
 */
public class Playlist {
    /**
     * List of songs in this playlist
     */
    private List<Song> playlist;

    //TODO Change to Room later?
    /**
     *Used for linking to room
     */
    private String roomId;

    //scales used for song vote evaluation
    private final int upScale = 51;
    private final int downScale = 49;

    /**
     * constructor used for playlist of a room
     * @param roomId room[Id] of the room having this playlist
     */
    public Playlist(String roomId){
        this.roomId = roomId;
        this.playlist = new ArrayList<>();
    }

    /**
     * constructor used for search results
     * @param roomId TODO remove
     * @param startList list from search results
     */
    public Playlist(String roomId, List<Song> startList){
        this(roomId);
        this.addAll(startList);
    }

    //Needs to tell frontend that user has upvoted because song is already in playlist

    /**
     * add a song to the playlist
     * @param song song to add
     * @return success boolean
     */
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

    /**
     * add a list of songs
     * @param songs song list
     * @return success boolean
     */
    public boolean addAll(List<Song> songs){
        boolean result = true;
        for(Song song : songs){
            result = result && this.add(song);
        }
        return result;
    }

    /**
     * remove a song to the playlist
     * @param song song to remove
     * @return success boolean
     */
    public boolean remove(Song song){
        return this.playlist.remove(song);
    }

    /**
     * evaluate a specific song
     * @param song said song
     * @return current rating of the song
     */
    public int evaluateSong(Song song){
        if(song==null){
            return 0;
        }
        int score = 0;
        score+=song.getUpVotes()*this.upScale;
        score-=song.getDownVotes()*this.downScale;
        return score;
    }

    /**
     * @return the first song in the list having the highest score
     */
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

    /**
     * convert this to a representative JSON
     * @return said JSONObject
     */
    public JSONObject toPlaySON(){
        JSONObject play = new JSONObject();
        JSONArray items = new JSONArray();
        for(Song song : playlist){
            items.add(song.toJSong());
        }
        play.put("items",items);
        return play;
    }

    /**
     * find a song by trackUID
     * @param trackUID said UID
     * @return song if found (else null)
     */
    public Song findSong(String trackUID){
        for(Song song : this.playlist){
            if(song.getTrackUID().equals(trackUID)){
                return song;
            }
        }
        return null;
    }

    /**
     * Vote for a song in this playlist
     * @param vote Vote as JSONObject [demoVote.json]
     * @return success boolean
     */
    public boolean voteByJSON(JSONObject vote){
        Song toVote = this.findSong((String) vote.get("trackUID"));
        if(toVote!=null) {
            if ((boolean) vote.get("vote")) {
                return toVote.upVote((boolean) vote.get("voteStyle"));
            }
            if (!((boolean) vote.get("vote"))) {
                return toVote.downVote((boolean) vote.get("voteStyle"));
            }
        }
        return false;
    }
}
