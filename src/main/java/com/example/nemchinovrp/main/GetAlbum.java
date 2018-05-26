package com.example.nemchinovrp.main;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetAlbum {
    private static final String clientId = "b2d16acc53824c18859e5f889f9ad544";
    private static final String clientSecret = "30a4b709b3a34972ac8475433355ea93";
    private static final String id = "5zT1JLIj9E57p3e1rFm9Uq";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();
    private static final GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id)
            .market(CountryCode.SE)
            .build();

    public static void main(String[] args) {
        getAlbum_Sync();
    }

    public static void getAlbum_Sync() {
        try {
            final Album album = getAlbumRequest.execute();

            System.out.println("Name: " + album.getName());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getAlbum_Async() {
        try {
            final Future<Album> albumFuture = getAlbumRequest.executeAsync();

            // ...

            final Album album = albumFuture.get();

            System.out.println("Name: " + album.getName());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        }
    }
}
