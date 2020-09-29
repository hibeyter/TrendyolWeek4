package com.trendyol.coucbase.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.trendyol.coucbase.common.Exceptions.PlaylistNotCreatedException;
import com.trendyol.coucbase.common.Queries;
import com.trendyol.coucbase.model.Playlist;
import com.trendyol.coucbase.model.Track;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PlaylistRepository {
    private final Cluster couchbaseCluster;
    private final Collection playListCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playListCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playListCollection = playListCollection;
    }

    public void insert(Playlist playlist) {
        try {
            playListCollection.insert(playlist.getId(), playlist);
        } catch (Exception e) {
            throw new PlaylistNotCreatedException(e.getMessage());
        }

    }

    public Playlist findById(String id) {
        GetResult getResult = playListCollection.get(id);
        return getResult.contentAs(Playlist.class);
    }

    public void deleteById(String id) {
        playListCollection.remove(id);
    }

    public List<Playlist> findByUserId(String userId) {
        String query = String.format(Queries.getAllPlayListWithUserId, userId);
        QueryResult queryResult = couchbaseCluster.query(query);
        return queryResult.rowsAs(Playlist.class);
    }

    public void addTrack(String id, Track track){
        Playlist playlist =  findById(id);
        playlist.getTracks().add(track);
        playlist.changeTrackCount();
        playListCollection.replace(playlist.getId(),playlist);
    }
    public void deleteTrack(String id, Track track){
        Playlist playlist =  findById(id);
        playlist.getTracks().remove(track);
        playlist.changeTrackCount();
        playListCollection.replace(playlist.getId(),playlist);
    }
}
