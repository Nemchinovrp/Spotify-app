package com.example.nemchinovrp.main;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.nemchinovrp.connect.ConnectToSpotifyServer.clientCredentials;

public class Main {
    private static final String id = "5zT1JLIj9E57p3e1rFm9Uq";

    public static void main(String[] args) throws IOException, SpotifyWebApiException {
//        album();
        searchTracks();
    }

    public static void album() throws IOException, SpotifyWebApiException {
        final GetAlbumRequest getAlbumRequest = clientCredentials().getAlbum(id)
                .market(CountryCode.SE)
                .build();
        try {
            final Album album = getAlbumRequest.execute();
            System.out.println("Name: " + album.getName());
            System.out.println(album.getLabel());
            Paging<TrackSimplified> tracks = album.getTracks();
            System.out.println("Total tracks: " + tracks.getTotal());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchTracks() throws IOException, SpotifyWebApiException {
        final SearchTracksRequest searchTracksRequest = clientCredentials().searchTracks("Scorpions")
                .market(CountryCode.SE)
                .limit(10)
                .offset(0)
                .build();
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();
            System.out.println("Total: " + trackPaging.getTotal());
            Track[] tracks = trackPaging.getItems();
            List<Track> trackList = Arrays.asList(tracks);
            for(Track track : trackList) {
                System.out.println(track.getName());
            }

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
