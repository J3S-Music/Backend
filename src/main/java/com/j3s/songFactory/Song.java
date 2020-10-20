package com.j3s.songFactory;

import org.json.simple.JSONObject;

public class Song {
    protected String track;
    protected String artist;
    protected String album;
    protected JSONObject imageInformation;
    protected String trackUID;

    protected int upVotes;
    protected int downVotes;

    protected Song(String track, String artist, String album, JSONObject imageInformation, String trackUID){
        this.track = track;
        this.artist = artist;
        this.album = album;
        this.imageInformation = imageInformation;
        this.trackUID = trackUID;
        this.upVotes = 0;
        this.downVotes = 0;
    }

    protected Song(JSONObject jSong){
        this((String) jSong.get("track"),(String) jSong.get("artist"),(String) jSong.get("album"),(JSONObject) jSong.get("imageInformation"),(String) jSong.get("trackUID"));
    }

    public JSONObject toSong(){
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
}
