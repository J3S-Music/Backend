package com.j3s.controller;

import com.j3s.SpotifyDsl.SpotifyDsl;
import com.j3s.model.UserConnection;
import com.j3s.playlistHandler.Playlist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class SearchController {

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JSONArray search(@RequestParam String q) throws IOException, ParseException {
        return SpotifyDsl.findSongList(q);
    }
}
