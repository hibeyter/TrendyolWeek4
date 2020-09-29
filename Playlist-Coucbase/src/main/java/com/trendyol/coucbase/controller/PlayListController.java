package com.trendyol.coucbase.controller;

import com.couchbase.client.core.error.UserNotFoundException;
import com.trendyol.coucbase.common.Exceptions.PlayListNotFoundException;
import com.trendyol.coucbase.common.Exceptions.PlaylistNotCreatedException;
import com.trendyol.coucbase.model.ErrorModel;
import com.trendyol.coucbase.model.Playlist;
import com.trendyol.coucbase.model.Track;
import com.trendyol.coucbase.service.PlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/playlist")
public class PlayListController {

    private final PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }


    @PostMapping
    public ResponseEntity<Void> createPlayList(@RequestBody Playlist playlist) {
        try {
            return ResponseEntity.created(playListService.addPlaylist(playlist)).build();
        } catch (UserNotFoundException e) {
            return new ResponseEntity(
                    createError("User not found", "Nothing belonging to this id found"),
                    HttpStatus.NOT_FOUND
            );
        } catch (PlaylistNotCreatedException e) {
            return new ResponseEntity(
                    createError("Playlist not created", "URI problem"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Playlist> getOnePlayList(@PathVariable String id) {
        try {
            Playlist playlist = playListService.findById(id);
            return ResponseEntity.ok(playlist);
        } catch (PlayListNotFoundException e) {
            return new ResponseEntity(
                    createError("Playlist not found", "Nothing belonging to this id found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayList(@PathVariable String id) {
        try {
            playListService.deleteFindById(id);
            return ResponseEntity.ok().build();
        } catch (PlayListNotFoundException e) {
            return new ResponseEntity(
                    createError("Playlist not found", "Nothing belonging to this id found"),
                    HttpStatus.NOT_FOUND
            );
        }

    }

    @GetMapping
    public ResponseEntity<List<Playlist>> findAllUserPlayList(@RequestParam("user-id") String userId) {
        try {
            return ResponseEntity.ok(playListService.findAllByUserId(userId));
        } catch (UserNotFoundException e) {
            return new ResponseEntity(
                    createError("User not found", "Nothing belonging to this id found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("{id}/tracks")
    public ResponseEntity<Void> addTrack(@PathVariable String id, @RequestBody Track track) {
        try {
            playListService.addTrack(id, track);
            return ResponseEntity.ok().build();
        } catch (PlayListNotFoundException e) {
            return new ResponseEntity(
                    createError("Playlist not found", "Nothing belonging to this id found"),
                    HttpStatus.NOT_FOUND
            );
        }

    }

    @DeleteMapping("{id}/tracks")
    public ResponseEntity<Playlist> deleteTrack(@PathVariable String id, @RequestBody Track track) {
        try {
            playListService.deleteTrack(id, track);
            return ResponseEntity.ok().build();
        } catch (PlayListNotFoundException e) {
            return new ResponseEntity(
                    createError("Playlist not found", "Nothing belonging to this id found"),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    public List<ErrorModel> createError(String message, String messageDetail) {
        List<ErrorModel> errorModels = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel(message, messageDetail);
        errorModels.add(errorModel);
        return errorModels;
    }

}
