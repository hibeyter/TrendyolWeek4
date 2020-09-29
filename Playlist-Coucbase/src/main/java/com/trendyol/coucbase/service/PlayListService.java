package com.trendyol.coucbase.service;


import com.couchbase.client.core.error.DocumentNotFoundException;
import com.trendyol.coucbase.common.Exceptions.PlayListNotFoundException;
import com.trendyol.coucbase.common.Exceptions.PlaylistNotCreatedException;
import com.trendyol.coucbase.common.Exceptions.UserNotFoundException;
import com.trendyol.coucbase.model.Playlist;
import com.trendyol.coucbase.model.Track;
import com.trendyol.coucbase.repository.PlaylistRepository;
import org.springframework.stereotype.Service;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Service
public class PlayListService {

    private final PlaylistRepository playlistRepository;

    public PlayListService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }


    public URI addPlaylist(Playlist playlist) {
        try {
            playlistRepository.insert(playlist);
            return new URI(String.format("/playlist/%s", playlist.getId()));
        } catch (PlaylistNotCreatedException | URISyntaxException e) {
            throw new PlaylistNotCreatedException(e.getMessage());
        }
    }

    public Playlist findById(String id) {
        try {
            return playlistRepository.findById(id);
        } catch (DocumentNotFoundException e) {
            throw new PlayListNotFoundException(e.getMessage());
        }
    }

    public void deleteFindById(String id) {
        try {
            playlistRepository.deleteById(id);
        } catch (DocumentNotFoundException e) {
            throw new PlayListNotFoundException(e.getMessage());
        }
    }

    public List<Playlist> findAllByUserId(String userId) {
        return playlistRepository.findByUserId(userId);
    }

    public void addTrack(String id, Track track) {
        try {
            playlistRepository.addTrack(id,track);
        } catch (DocumentNotFoundException e) {
            throw new PlayListNotFoundException(e.getMessage());
        }
    }

    public void deleteTrack(String id, Track track) {
        try {
            playlistRepository.deleteTrack(id,track);
        } catch (DocumentNotFoundException e) {
            throw new PlayListNotFoundException(e.getMessage());
        }
    }
}
