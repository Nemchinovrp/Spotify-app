package com.example.nemchinovrp.main;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;

import java.io.IOException;

import static com.example.nemchinovrp.connect.ConnectToSpotifyServer.clientCredentials;

public class Main {
    private static final String id = "5zT1JLIj9E57p3e1rFm9Uq";

    public static void main(String[] args) throws IOException, SpotifyWebApiException {
        album();
    }

    public static void album() throws IOException, SpotifyWebApiException {
        final GetAlbumRequest getAlbumRequest = clientCredentials().getAlbum(id)
                .market(CountryCode.SE)
                .build();
        try {
            final Album album = getAlbumRequest.execute();

            System.out.println("Name: " + album.getName());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
