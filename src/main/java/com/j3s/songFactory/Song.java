package com.j3s.songFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Song {
    protected String track;
    protected String artist;
    protected String album;
    protected JSONArray imageInformation;
    protected String trackUID;

    protected int upVotes;
    protected int downVotes;

    protected Song(String track, String artist, String album, JSONArray imageInformation, String trackUID){
        this.track = track;
        this.artist = artist;
        this.album = album;
        this.imageInformation = imageInformation;
        this.trackUID = trackUID;
        this.upVotes = 0;
        this.downVotes = 0;
    }

    protected Song(JSONObject jSong){
        this((String) jSong.get("track"),(String) jSong.get("artist"),(String) jSong.get("album"),(JSONArray) jSong.get("imageInformation"),(String) jSong.get("trackUID"));
    }

    public JSONObject toJSong(){
        JSONObject song = new JSONObject();
        song.put("track",this.track);
        song.put("artist",this.artist);
        song.put("album",this.album);
        song.put("imageInformation",this.imageInformation);
        song.put("trackUID",this.trackUID);
        song.put("upVotes",this.upVotes);
        song.put("downVotes",this.downVotes);
        return song;
    }

    public boolean upVote(boolean input){
        if(input) {
            this.upVotes++;
            return true;
        }else if(this.upVotes>0){
            this.upVotes--;
            return true;
        }
        return false;
    }

    public boolean downVote(boolean input){
        if(input) {
            this.downVotes++;
            return true;
        }else if(this.downVotes>0){
            this.downVotes--;
            return true;
        }
        return false;
    }

    public int getUpVotes(){
        return this.upVotes;
    }

    public int getDownVotes(){
        return this.downVotes;
    }

    public String getTrack(){
        return this.track;
    }
    public String getArtist(){
        return this.artist;
    }
    public String getAlbum(){
        return this.album;
    }
    public String getTrackUID(){
        return this.trackUID;
    }
}
